<template>
  <navbar />
  <section></section>

  <section
    style="
      width: 40%;
      margin-right: auto;
      margin-left: auto;
      border-radius: 0.5rem;
      box-shadow: 1px 4px 41px rgba(0, 0, 0, 0.1);
      background: #eeeff1;
    "
  >
    <h1><a href="/owner/login">Owner Login</a> | User Login</h1>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="exampleInputEmail1">Email address</label>
        <input
          type="email"
          class="form-control"
          id="exampleInputEmail1"
          aria-describedby="emailHelp"
          placeholder="Enter email"
          v-model="email"
        />
        <small id="emailHelp" class="form-text text-muted"
          >We'll never share your email with anyone else.</small
        >
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input
          type="password"
          class="form-control"
          id="exampleInputPassword1"
          placeholder="Password"
          v-model="password"
        />
      </div>
      <br />
      <button type="submit" class="btn btn-primary">Login</button>
    </form>
  </section>
</template>
<script>

import Navbar from "../Navbar.vue";
import axios from "axios";



export default {
  components: { Navbar },
  name: "userLogin",
  data() {
    return {
      email: "",
      password: "",
      latitude: null,
      longitude: null,
    };
  },
  methods: {
    login() {
      console.warn(this.email, this.password);
      const data = {
        email: this.email,
        password: this.password,
        latitude: this.latitude,
        longitude: this.longitude,
      };

      axios
        .post("/api/user/login", data)
        .then(() => {
          alert("logged in");
          window.location.href = "/user/dashboard";
        })
        .catch((error) => {
          // Handle login error
          alert("couldn't");
          console.error(error);
          // Display error message to the user
        });
      // console.warn(this.email, this.password);

      // Get user's geolocation
      // if (navigator.geolocation) {
      //   navigator.geolocation.getCurrentPosition(
      //     (position) => {
      //       this.latitude = position.coords.latitude;
      //       this.longitude = position.coords.longitude;

      //       console.log("Latitude", this.latitude);
      //       console.log("Longitude", this.longitude);

            
      //       this.$store.dispatch('setUserLocation', {latitude: this.latitude, longitude: this.longitude});


      //       // Proceed with login
      //       const data = {
      //         email: this.email,
      //         password: this.password,
      //         latitude: this.latitude,
      //         longitude: this.longitude,
      //       };
      //       axios
      //         .post("/api/user/login", data)
      //         .then(() => {
      //           alert("logged in");
      //           window.location.href = "/user/dashboard";
      //         })
      //         .catch((error) => {
      //           // Handle login error
      //           alert("couldn't");
      //           console.error(error);
      //           // Display error message to the user
      //         });
      //     },
      //     (error) => {
      //       console.error("Error getting geolocation:", error);
      //       // Proceed with login without geolocation
      //       const data = {
      //         email: this.email,
      //         password: this.password,
      //       };
      //       axios
      //         .post("/api/user/login", data)
      //         .then(() => {
      //           alert("logged in");
      //           window.location.href = "/user/dashboard";
      //         })
      //         .catch((error) => {
      //           // Handle login error
      //           alert("couldn't");
      //           console.error(error);
      //           // Display error message to the user
      //         });
      //     }
      //   );
      // } else {
      //   alert("geolocation not supported");
      //   //Geolocation not supported, proceed with login
      //   const data = {
      //     email: this.email,
      //     password: this.password,
      //   };
      //   axios
      //     .post("/api/user/login", data)
      //     .then(() => {
      //       alert("logged in");
      //       window.location.href = "/user/dashboard";
      //     })
      //     .catch((error) => {
      //       // Handle login error
      //       alert("couldn't");
      //       console.error(error);
      //       // Display error message to the user
      //     });
      // }
    },
  },
};
</script>