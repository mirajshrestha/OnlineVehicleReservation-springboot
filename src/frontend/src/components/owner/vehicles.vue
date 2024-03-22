<template>
  <div v-if="loggedIn">
    <div class="container-fluid">
      <div class="row flex-nowrap">
        <div
          class="bg-dark col-auto col-md-4 col-lg-3 min-vh-100 d-flex flex-column justify-content-between"
        >
          <div class="bg-dark p-2">
            <a
              href=""
              class="d-flex text-decoration-none mt-1 align-items-center text-white"
            >
              <span class="fs-4 d-none d-sm-inline">Owners Dashboard</span>
            </a>
            <ul class="nav nav-pills flex-column mt-4">
              <li class="nav-item py-2 py-sm-0">
                <a href="/owner/dashboard" class="nav-link text-white"
                  ><i class="bx bxs-dashboard"></i
                  ><span class="fs-6 d-none ms-3 d-sm-inline"
                    >Dashboard</span
                  ></a
                >
              </li>
              <li class="nav-item py-2 py-sm-0">
                <a href="/owner/profile" class="nav-link text-white"
                  ><i class="bx bxs-user-circle"></i
                  ><span class="fs-6 d-none ms-3 d-sm-inline">Profile</span></a
                >
              </li>
              <li class="nav-item py-2 py-sm-0">
                <a href="/owner/vehicle" class="nav-link active"
                  ><i class="bx bx-list-ul"></i
                  ><span class="fs-6 d-none ms-3 d-sm-inline">Vehicles</span></a
                >
              </li>
              <li class="nav-item py-2 py-sm-0">
                <a href="#" class="nav-link text-white"
                  ><i class="bx bx-book-content"></i
                  ><span class="fs-6 d-none ms-3 d-sm-inline">Bookings</span></a
                >
              </li>
            </ul>
          </div>
          <div class="dropdown open p-3">
            <button
              class="btn border-none dropdown-toggle text-white"
              type="button"
              id="triggerId"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <i class="bx bxs-user-circle"></i
              ><span class="ms-2">{{ owner.fullname }}</span>
            </button>
            <div class="dropdown-menu" aria-labelledby="triggerId">
              <button class="dropdown-item" href="#">Profile</button>
              <button class="dropdown-item" href="#" @click="logout">
                Logout
              </button>
            </div>
          </div>
        </div>

        <!-- CONTENT  -->

        <div class="p-3">
          <h2>
            Vehicles |
            <a href="/owner/add-vehicle" class="btn btn-primary">Add vehicle</a>
          </h2>
          <hr />
          <table class="table table-striped" style="width: 75%">
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
                  <button
                    v-if="vehicle.feature === null"
                    class="btn btn-primary"
                    @click="requestFeature(vehicle.vehicle_id)"
                  >
                    Request
                  </button>
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
    </div>
  </div>
</template>

<style>
.nav-pills li a:hover {
  background-color: blue;
}
</style>

<script>
import axios from "axios";
export default {
  name: "vehicleDashboard",
  data() {
    return {
      owner: {},
      loggedIn: false,
      vehicles: [],
    };
  },
  mounted() {
    fetch("/api/vehicle/owner")
      .then((response) => response.json())
      .then((data) => {
        this.vehicles = data;
        // console.log("carimage:", vehicles.carimg);
      });
  },
  created() {
    this.fetchUserData();
  },
  methods: {
    getSerialNumber(index) {
      return index + 1;
    },
    editVehicle(vehicleId) {
      // Navigate to the edit page with the vehicle ID
      this.$router.push({ name: "editVehicle", params: { vehicleId } });
      console.log("Vehicle ID: ", vehicleId);
    },
    requestFeature(vehicleId) {
      console.log("Requesting feature for vehicle: ", vehicleId);
      axios
        .put(`/api/vehicle/request-feature/${vehicleId}`)
        .then((response) => {
          console.log(response.data);
          alert("Feature Requested Successfully!");
          location.reload();
          // You might want to fetch the vehicles again after the feature request
          this.fetchVehicles();
        })
        .catch((error) => {
          console.error(error);
        });
    },
    getFullImagePath(imagePath) {
      // Replace this with the actual URL of your backend
      const backendUrl = "http://localhost:3000/";
      const normalizedPath = imagePath.replace(/\\/g, "/");
      const relativePath = normalizedPath.replace(/^.*uploads/, "");
      return backendUrl + relativePath;
      // return backendUrl + imagePath.replace("C:\\uploads", "");
    },
    fetchUserData() {
      axios
        .get("/api/owner/me")
        .then((response) => {
          this.owner = response.data;
          this.loggedIn = true;
        })
        .catch((error) => {
          console.error(error);
          this.$router.push("/owner/login");
        });
    },

    deleteVehicle(vehicleId) {
      console.log("delete vehicle working");
      axios
        .delete(`/api/vehicle/${vehicleId}`)
        .then((response) => {
          console.log(response.data);
          alert("Vehicle Deleted Succesfully!");
          location.reload();
        })
        .catch((error) => {
          console.log(error);
        });
    },

    logout() {
      console.log("logout working");
      axios
        .get("/api/owner/logout")
        .then(() => {
          window.location.href = "/owner/login";
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>