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
        <b-icon pack="fas" icon="sync-alt" />
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
// import axios from "axios";

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

        // ctx.drawImage(video, (width / 2) * -1, 0.5, width, height);
      } else {
        ctx.drawImage(video, 0, 0);
      }
      ctx.restore();
      this.photo = {
        id: this.counter++,
        src: canva.toDataURL("image/png"),
      };
      console.log(dx, dy, dw, dh, width, height);
      console.log(this.photo.src);

      // const body = document.querySelector("body");
      // let canva2 = this.$refs.canva;
      // let ctx2 = canva2.getContext("2d");
      // const image = new Image();
      // //image객체가 생성되어 속성들을 추가할수 있음
      // image.src = this.photo.src;
      // body.appendChild(image);
      // ctx2.drawImage(image, dx, dy, 1, 1, dw, dh);

      // console.log(image, "wpqkf");
      //   var image = document.getElementById('myImage'),
      // canvas = document.createElement('canvas'),
      // ctx = canvas.getContext('2d');
      // const URL = "http://localhost:5000/fileUpload";
      // axios
      //   .post(
      //     URL,
      //     { image: this.photo[0].src }
      //     // {
      //     //   headers: { "Content-Type": "multipart/form-data" },
      //     // }
      //   )
      //   .then((res) => {
      //     console.log(res);
      //   });

      // ctx.drawImage(this.photo, 33, 71, 104, 124, 21, 20, 87, 104);
      // console.log(this.photo);
      // var image = new Image();
      // image.src = this.photo[0].src;
      // var croppingCoords = {
      //   x: this.width / 4,
      //   y: this.height / 4,
      //   width: this.width / 2,
      //   height: this.height / 2,
      // };
      // var cc = croppingCoords;
      // var workCan = document.createElement("canvas"); // create a canvas
      // workCan.width = Math.floor(cc.width); // set the canvas resolution to the cropped image size
      // workCan.height = Math.floor(cc.height);
      // var ctx2 = workCan.getContext("2d"); // get a 2D rendering interface
      // ctx2.drawImage(image, -Math.floor(cc.x), -Math.floor(cc.y)); // draw the image offset to place it correctly on the cropped region
      // image.src = workCan.toDataURL(); // set the image source to the canvas as a data URL
      // console.log(image);
    },
    async switchCamera() {
      this.switchingCamera = true;
      const tracks = this.mediaStream.getVideoTracks();
      // console.log(this.mediaStream), "ff";
      // console.log(this.mediaStream.getVideoTracks());
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
  