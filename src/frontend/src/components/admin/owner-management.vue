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
              <router-link to="/admin" class="nav-link">Dashboard</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/admin/ownerManagement" class="nav-link active"
                >Owner Management</router-link
              >
            </li>

            <li class="nav-item">
              <router-link to="/admin/feature-requests" class="nav-link"
                >Feature Requests</router-link
              >
            </li>
            <li class="nav-item">
              <button class="btn btn-danger ml-auto" @click="logout">
                Logout
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Dashboard Content -->
    <div class="container mt-4">
      <h2>Owner Management</h2>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Contact</th>

            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(owner, index) in owners" :key="owner.id">
            <td>{{ getSerialNumber(index) }}</td>
            <td>{{ owner.fullname }}</td>
            <td>{{ owner.email }}</td>
            <td>{{ owner.contact }}</td>

            <td>
              <button
                @click="deleteOwner(owner.owners_id)"
                class="btn btn-danger"
              >
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
    
    <script>
import axios from "axios";

export default {
  name: "ownerManagement",
  data() {
    return {
      admin: {},
      loggedIn: false,
      owners: [],
    };
  },
  created() {
    this.fetchAdminData();
    this.fetchOwners();
  },
  methods: {
    fetchOwners() {
      // Make an axios request to get owner data
      axios.get("/api/owner/all").then((response) => {
        this.owners = response.data;
        console.log("Owners: ", this.owners);
      });
    },
    getSerialNumber(index) {
      return index + 1;
    },
    deleteOwner(ownerId) {
      console.log("Deleting owner: ", ownerId);
      // Assuming you have an API endpoint to delete an owner
      axios.delete(`/api/owner/${ownerId}`).then(() => {
        // After deleting, fetch the owners again to update the table
        this.fetchOwners();
      });
    },

    fetchAdminData() {
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
    