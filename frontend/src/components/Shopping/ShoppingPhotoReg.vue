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
        <input type="file" @change="testImage()" id="inputImage" />
      </div>
    </div>
  </div>
</template>
  
  <script>
import "@/assets/css/components/Shopping/shoppingPhotoReg.scss";
import axios from "axios";
import SERVER from "@/api/spring";

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
    testImage() {
      const formdata = new FormData();
      formdata.append("image", document.getElementById("inputImage").files[0]);
      axios
        .post(SERVER.CAMERAURL, formdata, {
          "Content-Type": "multipart/form-data",
        })
        .then((res) => {
          console.log(res);
        });
    },
    async StartRecording(facingMode) {
      this.facingMode = facingMode;
      let video = this.$refs.video;
      // console.log(video);
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
        // ctx.drawImage(video, width * -1, 0, width, height);
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
      // const formData = new FormData();
      // formData.append("image", )
      console.log(this.photo, typeof this.photo);
      // this.photo = canva.toDataURL({ format: "png" });
      const file = new File();
      file.src = this.photo.src;
      console.log(file, typeof file);

      // formData.append("image", this.photo);
      axios
        .post(SERVER.CAMERAURL, file, {
          "Content-Type": "multipart/form-data",
        })
        .then((res) => console.log(res));

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
    };
  },
};
</script>
  