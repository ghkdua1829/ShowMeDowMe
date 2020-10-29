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
      <!-- 사진 찍어주는 버튼 -->
      <div class="photo-button-container">
        <v-icon large color="white" class="button photo-button" @click="TakePhoto">
          mdi-circle
          <!-- <b-icon pack="fas" icon="camera" /> -->
        </v-icon>
      </div>
      <!-- <photos-gallery class="gallery" :photos="photos" /> -->
    </div>
  </div>
  
</template>

<script>
import "@/assets/css/components/Shopping/shoppingPhotoReg.scss";

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
      console.log(video);
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
      canva.width = width;
      canva.height = height;
      let ctx = canva.getContext("2d");
      ctx.save();

      if (this.facingMode === "user") {
        ctx.scale(-1, 1);
        ctx.drawImage(video, width * -1, 0, width, height);
      } else {
        ctx.drawImage(video, 0, 0);
      }
      ctx.restore();
      this.photos.push({
        id: this.counter++,
        src: canva.toDataURL("image/png"),
      });
      console.log(this.photos)
    },
    async switchCamera() {
      this.switchingCamera = true;
      const tracks = this.mediaStream.getVideoTracks();
      console.log(this.mediaStream), "ff"
      console.log(this.mediaStream.getVideoTracks())
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
      photos: [],
      mediaStream: null,
      videoDevices: [],
      facingMode: "environment",
      counter: 0,
      switchingCamera: false,
    };
  },

};
</script>


