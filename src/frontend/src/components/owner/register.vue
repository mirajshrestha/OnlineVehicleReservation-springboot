<template>
  <navbar />
  <section></section>
  <!-- <h3>Owner Registration | <a href="/user/signup">User Registration</a><router-link :to="{name: 'userRegister'}">User Registration</router-link></h3> -->
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
    <h3>Owner Registration | <a href="/user/signup">User Registration</a></h3>

    <form @submit.prevent="save">
      <!-- <label for="">Full Name: </label> -->
      <input
        type="text"
        v-model="owner.fullname"
        class="form-control"
        placeholder="Full Name"
      />
      <span v-if="!owner.fullname" class="error" style="color: red"
        >Full Name is required</span
      ><br />
      <!-- <label for="">Email:</label><br /> -->
      <input
        type="email"
        name=""
        id=""
        v-model="owner.email"
        class="form-control"
        placeholder="Email"
      />
      <span v-if="!owner.email" class="error" style="color: red"
        >Email is required</span
      >
      <span
        v-if="owner.email && !isValidEmail(owner.email)"
        class="error"
        style="color: red"
        >Invalid email format</span
      >
      <br />

      <!-- <label for="">Password:</label> -->
      <input
        type="password"
        name=""
        id=""
        v-model="owner.password"
        class="form-control"
        placeholder="Password"
      />
      <span v-if="!owner.password" class="error" style="color: red"
        >Password is required</span
      >
      <span
        v-if="owner.password && owner.password.length < 6"
        class="error"
        style="color: red"
        >Password should be at least 6 characters</span
      >
      <br />
      <!-- <label for="">Mobile Number:</label> -->
      <input
        type="text"
        name=""
        id=""
        v-model="owner.contact"
        class="form-control"
        placeholder="Mobile Number"
      />
      <span v-if="!owner.contact" class="error" style="color: red"
        >Mobile Number is required</span
      >
      <span
        v-if="owner.contact && !/^\d{10}$/.test(owner.contact)"
        class="error"
        style="color: red"
        >Incorrect format</span
      >
      <br />

      <br />
      <input type="submit" value="Register" class="btn btn-primary" />
    </form>
  </section>
</template>
<script>
import Navbar from "../Navbar.vue";

export default {
  components: { Navbar },
  name: "ownerRegister",
  data() {
    return {
      owner: {
        owners_id: "",
        fullname: "",
        email: "",
        password: "",
        contact: "",
        // role: "OWNER"
      },
    };
  },
  methods: {
    isValidEmail(email) {
      // Basic email format validation
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return emailRegex.test(email);
    },
    save() {
      if (
        !this.owner.fullname ||
        !this.owner.email ||
        !this.owner.password ||
        
        !this.owner.contact
      ) {
        console.log("Please fill in all the fields.");
        return;
      }

      if (this.owner.password.length < 6) {
        console.log("Password should be at least 6 characters long.");
        return;
      }

      if (!/^\d{10}$/.test(this.owner.contact)) {
        console.log("Mobile number should be exactly 10 digits.");
        return;
      }

      if (!this.isValidEmail(this.owner.email)) {
        console.log("Invalid email format.");
        return;
      }

      console.log("Form passed validation. Submitting...");
      fetch("/api/owner/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          fullname: this.owner.fullname,
          email: this.owner.email,
          password: this.owner.password,
          contact: this.owner.contact,
          // role: this.owner.role
        }),
      }).then(() => {
        alert("Registerd successfully"),
          (window.location.href = "/owner/login");
      });
    },
  },
};
</script>
<!-- <style src="../../../public/assets/css/style.css"></style> -->