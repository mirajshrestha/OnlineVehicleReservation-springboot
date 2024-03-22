<template>
  <div v-if="loggedIn">
    <!-- Navigation Panel -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
        <a class="navbar-brand" href="/">ONLINE VEHICLE RESERVATION</a>
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <router-link to="/admin" class="nav-link active"
                >Dashboard</router-link
              >
            </li>
            <li class="nav-item">
              <router-link to="/admin/owner" class="nav-link"
                >Owner Management</router-link
              >
            </li>
            
            <li class="nav-item">
              <router-link to="/admin/feature-requests" class="nav-link"
                >Feature Requests</router-link
              >
            </li>
            <li class="nav-item ">
              <button class="btn btn-danger ml-auto" @click="logout">Logout</button>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Dashboard Content -->
    <div class="container mt-4">
      <!-- Include your dashboard content directly here -->
      <h2>Welcome to the Admin Dashboard</h2>
      <p>
        This is the dashboard content. You can customize this section based on
        your requirements.
      </p>
    </div>
  </div>
</template>
  
  <script>
import axios from "axios";

export default {
  name: "adminDashboard",
  data() {
    return {
      admin: {},
      loggedIn: false,
    };
  },
  created() {
    this.fetchAdminData();
  },
  methods: {
    fetchAdminData(){
        axios
        .get("/api/admin/me")
        .then((response) => {
          this.user = response.data;
          this.loggedIn = true;
        })
        .catch((error) => {
          console.error(error);
          this.$router.push("/admin/login");
        });
    },
    logout() {
      axios
        .get("/api/admin/logout")
        .then(() => {
          console.log("Logout successful");
          window.location.href = "/admin/login";
          // Redirect or perform other actions after logout
        })
        .catch((error) => {
          console.error("Logout failed", error);
          // Handle logout failure
        });
      console.log("Logout clicked");
    },
  },
};
</script>
  
  <style>
/* Add your custom styles here */
</style>
  