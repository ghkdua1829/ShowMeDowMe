<template>
  <div class="shoppingPhotoReg">
    <v-btn dark block @click="$router.push({ path: '/shop' })">
      <h2>이전으로 이동</h2>
    </v-btn>
    <div class="wrapper">
      <video
        class="video"
        :class="facingMode === 'user' ? 'front' : ''"
        ref="video"
      />
      <canvas style="display: none" ref="canva" />

      <!-- 앞뒤 전환 버튼 -->
      <button
        v-if="videoDevices.length > 1"
        class="button is-rounded is-outlined switch-button"
        @click="switchCamera"
        :disabled="switchingCamera"
      >
        <v-icon>mdi-unfold-more-vertical</v-icon>
      </button>
      <div class="box-border">
        <h4>가격표를 영역 안에 넣어주세요.</h4>
        <div class="box"></div>
      </div>
      <!-- 사진 찍어주는 버튼 -->
      <div class="photo-button-container">
        <v-icon
          large
          color="white"
          class="button photo-button"
          @click="TakePhoto"
        >
          mdi-circle
        </v-icon>
      </div>
    </div>
  </div>
</template>
  
  <script>
import "@/assets/css/components/Shopping/shoppingPhotoReg.scss";
import axios from "axios";
import SERVER from "@/api/spring";
import { mapActions } from "vuex";

export default {
  name: "ShoppingPhotoReg",
  async mounted() {
    const devices = await navigator.mediaDevices.enumerateDevices();
    this.videoDevices = devices.filter((d) => d.kind === "videoinput");
    await this.StartRecording(
      this.videoDevices.length === 1 ? "user" : "environment"
    );
  },
  methods: {
    ...mapActions(["saveCameraData"]),
    dataURLtoFile(dataurl, fileName) {
      var arr = dataurl.split(","),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);

      while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
      }

      return new File([u8arr], fileName, { type: mime });
    },

    async StartRecording(facingMode) {
      this.facingMode = facingMode;
      let video = this.$refs.video;
      this.mediaStream = await navigator.mediaDevices.getUserMedia({
        video: { facingMode: facingMode },
      });
      video.srcObject = this.mediaStream;
      return await video.play();
    },
    checkimage() {},
    async TakePhoto() {
      let video = this.$refs.video;
      let canva = this.$refs.canva;
      let width = video.videoWidth;
      let height = video.videoHeight;
      let dw = width * 0.8;
      let dh = height * 0.3;
      let dx = (dw / 2) * -1;
      let dy = dh / 2;
      canva.width = width;
      canva.height = height;
      let ctx = canva.getContext("2d");
      ctx.save();

      if (this.facingMode === "user") {
        ctx.scale(-1, 1);
        ctx.drawImage(
          video,
          200,
          150,
          dw * 1.2,
          dh * 1.2,
          dx,
          dy,
          dw * 1.2,
          dh * 1.2
        ); // 이미지객체, -dx, dy
      } else {
        ctx.drawImage(video, 0, 0);
      }
      ctx.restore();
      this.photo = {
        src: canva.toDataURL("image/png"),
      };
      const formData = new FormData();
      var priceImage = this.dataURLtoFile(this.photo.src, "price.png");
      formData.append("image", priceImage);
      axios
        .post(SERVER.CAMERAURL, formData, {
          "Content-Type": "multipart/form-data",
        })
        .then((res) => {
          if (res.status === 200) {
            this.saveData = res.data;
            this.saveData.amount = 1;
            // console.log(this.saveData);
            this.saveCameraData(this.saveData);
          }
        })
        .catch((error) => {
          alert("인식을 하지 못하였습니다. 다시 한번 촬영해주세요.");
          console.err(error);
        });

      // this.$router.push({ path: "/camera/result" });
    },
    async switchCamera() {
      this.switchingCamera = true;
      const tracks = this.mediaStream.getVideoTracks();

      tracks.forEach((track) => {
        track.stop();
      });
      await this.StartRecording(
        this.facingMode === "environment" ? "user" : "environment"
      );
      this.switchingCamera = false;
    },
  },
  data() {
    return {
      photo: [],
      mediaStream: null,
      videoDevices: [],
      facingMode: "environment",
      counter: 0,
      switchingCamera: false,
      saveData: [],
    };
  },
};
</script>
  