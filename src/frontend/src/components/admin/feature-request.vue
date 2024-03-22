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
              <router-link to="/admin/owner" class="nav-link"
                >Owner Management</router-link
              >
            </li>

            <li class="nav-item">
              <router-link to="/admin/feature-requests" class="nav-link active"
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
      <h2>Feature Request</h2>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>SN</th>
            <th>Image</th>
            <th>Manufacturer</th>
            <th>Model</th>
            <th>Rate</th>
            <th>From</th>
            <th>To</th>
            <th>Location</th>
            <th>Feature</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(vehicle, index) in vehicles" :key="vehicle.id">
            <td>{{ getSerialNumber(index) }}</td>
            <td>
              <img
                :src="`http://localhost:3000/api/images/${vehicle.carimg}`"
                alt="Vehicle Image"
                style="width: 100px; height: 100px"
              />

              {{ console.log("car image", vehicle.carimg) }}
            </td>
            <td>{{ vehicle.manufacturer }}</td>
            <td>{{ vehicle.model }}</td>
            <td>Rs. {{ vehicle.dailyfare }}/day</td>
            <td>{{ vehicle.fromdate }}</td>
            <td>{{ vehicle.todate }}</td>
            <td>{{ vehicle.availablelocation }}</td>
            <td>
              <template v-if="vehicle.feature === 'Requested'">
                <button
                  class="btn btn-success"
                  @click="acceptFeature(vehicle.vehicle_id)"
                >
                  Accept
                </button>
                &nbsp;
                <button
                  class="btn btn-danger"
                  @click="rejectFeature(vehicle.vehicle_id)"
                >
                  Reject
                </button>
              </template>
              <span v-else>{{ vehicle.feature }}</span>
            </td>
            <td>
              <button
                class="btn btn-secondary"
                @click="editVehicle(vehicle.vehicle_id)"
              >
                <i class="bx bxs-edit-alt"></i>
              </button>
              &nbsp;
              <button class="btn btn-danger">
                <i
                  class="bx bxs-trash-alt"
                  @click="deleteVehicle(vehicle.vehicle_id)"
                ></i>
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
  name: "featureRequests",
  data() {
    return {
      admin: {},
      loggedIn: false,
      owners: [],
      vehicles: [],
    };
  },
  created() {
    this.fetchAdminData();
    this.fetchOwners();
    this.fetchVehicles();
  },
  methods: {
    acceptFeature(vehicleId) {
      this.updateFeatureStatus(vehicleId, 'Accepted');
    },

    rejectFeature(vehicleId) {
      this.updateFeatureStatus(vehicleId, 'Rejected');
    },

    updateFeatureStatus(vehicleId, status) {
      axios
        .put(`/api/vehicle/${vehicleId}`, { feature: status })
        .then(() => {
          // After updating, fetch the vehicles again to update the table
          this.fetchVehicles();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    fetchOwners() {
      // Make an axios request to get owner data
      axios.get("/api/owner/all").then((response) => {
        this.owners = response.data;
        console.log("Owners: ", this.owners);
      });
    },
    fetchVehicles() {
      axios.get("/api/vehicle/all").then((response) => {
        // Filter vehicles where feature is null
        this.vehicles = response.data.filter(
          (vehicle) => vehicle.feature === "Requested"
        );
        console.log("Filtered Vehicles: ", this.vehicles);
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
      