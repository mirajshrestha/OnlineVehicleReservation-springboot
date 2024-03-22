<template>
  <router-view></router-view>
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
    <h3><a href="/ownersignup">Owner Registration</a> | User Resgistration</h3>
    <form @submit.prevent="save" enctype="multipart/form-data">
      <div class="form-group">
        <label for="">Full Name: </label>
        <input type="text" v-model="user.name" class="form-control" />
      </div>
      <div class="form-group">
        <label for="">Email:</label>
        <input
          type="email"
          name=""
          id=""
          v-model="user.email"
          class="form-control"
        />
      </div>
      <div class="form-group">
        <label for="">Password:</label>
        <input
          type="password"
          name=""
          id=""
          v-model="user.password"
          class="form-control"
        />
      </div>
      <div class="form-group">
        <label for="">Mobile Number: </label>
        <input
          type="text"
          name=""
          id=""
          v-model="user.contact"
          class="form-control"
        />
      </div>
      <div class="form-group">
        <label for="">Address: </label>
        <input
          type="text"
          name=""
          id=""
          v-model="user.address"
          class="form-control"
        />
      </div>
      <label for="">License Image:</label>
      <input type="file" @change="handleFileUpload" class="form-control" />

      <!-- <label for="">License: </label><br />
    <input type="file" name="licenseImage" @change="handleLicenseChange" accept="image/*"/><br /> -->
      <br />
      <input type="submit" value="Register" class="btn btn-primary" />
    </form>
  </section>
</template>
<script>
import axios from "axios";

import Navbar from "../Navbar.vue";
export default {
  components: { Navbar },
  name: "userRegister",
  data() {
    return {
      user: {
        user_id: "",
        name: "",
        email: "",
        password: "",
        contact: "",
        address: "",
        license_name: null,
        // role: "USER"
      },
    };
  },
  methods: {
    handleFileUpload(event) {
      this.user.license_name = event.target.files[0];
    },
    save() {
      if (this.user.name == "" || this.user.name == null) {
        alert("user is required!");
        return;
      } else {
        // fetch("/api/user/register", {
        //   method: "POST",
        //   headers: {
        //     Accpet: "application/json",
        //     "Content-Type": "application/json",
        //   },
        //   body: JSON.stringify({
        //     name: this.user.name,
        //     email: this.user.email,
        //     password: this.user.password,
        //     contact: this.user.contact,
        //     address: this.user.address,
        //     // role: this.user.role
        //   }),
        // }).then(() => {
        //   alert("Registerd successfully"),
        //     (window.location.href = "/user/dashboard");
        // });
        const formData = new FormData();

        const userData = {
          name: this.user.name,
          email: this.user.email,
          password: this.user.password,
          contact: this.user.contact,
          address: this.user.address,
          // role: this.user.role
        };

        formData.append(
          "user",
          new Blob([JSON.stringify(userData)], { type: "application/json" })
        );
        formData.append("image", this.user.license_name);

        axios
          .post("/api/user/register", formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then(() => {
            alert("Registerd successfully"),
              (window.location.href = "/user/dashboard");
          })
          .catch((error) => console.error("Error registering User:", error));
      }
    },
  },
};
</script>