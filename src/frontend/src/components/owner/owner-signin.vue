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
    <h1>Owner Login | <a href="/user/login">User Login</a></h1>
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
import axios from "axios";
import Navbar from "../Navbar.vue";
export default {
  components: { Navbar },
  name: "OwnerSignIn",
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    login() {
      console.warn(this.email, this.password);
      const data = {
        email: this.email,
        password: this.password,

      };

      axios
        .post("/api/owner/login", data)
        .then(() => {
          alert("Logged in");
          window.location.href = "/owner/dashboard";
        })
        .catch((error) => {
          alert("couldn't");
          console.error(error);
        });
    },
  },
};
</script>