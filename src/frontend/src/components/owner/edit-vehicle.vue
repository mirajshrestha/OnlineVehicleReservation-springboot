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
                <a href="/owner/bookings" class="nav-link text-white"
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
              <button class="dropdown-item" href="#" @click="logout">
                Logout
              </button>
            </div>
          </div>
        </div>

        <!-- CONTENT  -->

        <div class="p-3">
          <h2>
            Edit vehicle |
            <a href="/owner/vehicle" class="btn btn-secondary"
              ><i class="bx bx-arrow-back"></i> Back</a
            >
          </h2>
          <hr />
          <form @submit.prevent="updateVehicle">
            <label for="">Manufacturer: </label>
            <input
              type="text"
              v-model="vehicle.manufacturer"
              class="form-control"
            />
            <label for="">Model:</label><br />
            <input type="text" v-model="vehicle.model" class="form-control" />
            <label for="">Daily Fair:</label>
            <input
              type="number"
              v-model="vehicle.dailyfare"
              class="form-control"
            />
            <label for="">From Date:</label>
            <input
              type="date"
              v-model="vehicle.fromdate"
              class="form-control"
            />
            <label for="">To Date:</label>
            <input type="date" v-model="vehicle.todate" class="form-control" />
            <label for="">Available Location:</label>
            <select v-model="vehicle.availablelocation" class="form-control">
              <option value="">---Select Location---</option>
              <option
                v-for="location in locations"
                :key="location.location_id"
                :value="location.location"
              >
                {{ location.location }}
              </option>
            </select>
            <label for="">Vehicle Image:</label><br />
            <img
              :src="`http://localhost:3000/api/images/${vehicle.carimg}`"
              alt="Vehicle Image"
              style="width: 200px; height: 170px"
            />
            <br />
            <input
              type="hidden"
              name="owners_id"
              class="form-control"
              :value="owner.owners_id"
            />
            <label for="">Description:</label>
            <textarea
              cols="30"
              rows="10"
              v-model="vehicle.description"
              class="form-control"
            ></textarea>
            <!-- <input type="text" v-model="vehicle.description" class="form-control" /> -->
            <br />
            <input type="submit" value="Register" class="btn btn-primary" />
          </form>
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
  name: "addVehicle",
  data() {
    return {
      owner: {},
      loggedIn: false,
      vehicle: {
        manufacturer: "",
        model: "",
        dailyfare: "",
        fromdate: "",
        todate: "",
        availablelocation: "",
        description: "",
        owners_id: "",
        carimg: null,
      },
      locations: [],
    };
  },
  created() {
    this.fetchUserData();
    this.fetchLocations();
    this.fetchVehicleDetails();
  },
  methods: {
    handleFileUpload(event) {
      this.vehicle.carimg = event.target.files[0];
    },
    updateVehicle() {
      // Assuming you have a backend route like `/api/vehicle/update/:vehicleId`
      const vehicleId = this.$route.params.vehicleId;

      const updatedVehicleData = {
        manufacturer: this.vehicle.manufacturer,
        model: this.vehicle.model,
        dailyfare: this.vehicle.dailyfare,
        fromdate: this.vehicle.fromdate,
        todate: this.vehicle.todate,
        availablelocation: this.vehicle.availablelocation,
        description: this.vehicle.description,
        owners_id: this.vehicle.owners_id,
      };

      // Make a PUT or PATCH request to update the vehicle
      axios
        .put(`/api/vehicle/update/${vehicleId}`, updatedVehicleData)
        .then(() => {
          alert("Vehicle updated");
          // Redirect to the vehicle dashboard or any other page
          this.$router.push("/owner/vehicle");
        })
        .catch((error) => {
          console.error("Error updating vehicle:", error);
        });

      // ... other existing methods ...
    },
    fetchVehicleDetails() {
      // Get the vehicleId from the route parameters
      const vehicleId = this.$route.params.vehicleId;
      console.log(vehicleId);

      // Make an API call to get the details of the specific vehicle
      axios
        .get(`/api/vehicle/${vehicleId}`)
        .then((response) => {
          // Update the vehicle data with the fetched details
          this.vehicle = response.data;

          // Ensure that the fetched data matches the format of your form fields
        })
        .catch((error) => {
          console.error("Error fetching vehicle details:", error);
        });
    },
    registerVehicle() {
      const formData = new FormData();

      const vehicleData = {
        manufacturer: this.vehicle.manufacturer,
        model: this.vehicle.model,
        dailyfare: this.vehicle.dailyfare,
        fromdate: this.vehicle.fromdate,
        todate: this.vehicle.todate,
        availablelocation: this.vehicle.availablelocation,
        description: this.vehicle.description,
        owners_id: this.owner.owners_id,
      };

      formData.append(
        "vehicle",
        new Blob([JSON.stringify(vehicleData)], { type: "application/json" })
      );

      formData.append("image", this.vehicle.carimg);

      console.log("Image", this.vehicle.carimg);

      // formData.append("image", this.vehicle.carimg);
      // formData.append("vehicle", JSON.stringify(vehicleData));

      console.log("Image", this.vehicle.carimg);
      console.log("Owner: ", this.owner.owners_id);
      console.log("Vehicle: ", this.vehicle.manufacturer);
      console.log("Model: ", this.vehicle.model);
      console.log("Dailyfare: ", this.vehicle.dailyfare);
      console.log("Fromdate: ", this.vehicle.fromdate);
      console.log("Todate: ", this.vehicle.todate);
      console.log("Availablelocation: ", this.vehicle.availablelocation);
      console.log("Description: ", this.vehicle.description);

      console.log("working");

      console.log("Formdata:", formData);
      // fetch("/api/vehicle/add", {
      //   method: "POST",
      //   body: formData,
      // })
      //   .then((response) => {
      //     if(!response.ok){
      //       throw new Error("HTTP error! Status: ${response.status}");
      //     }
      //     return response.json();
      //   })
      //   .catch((error) => console.error("Error registering vehicle: ", error));

      axios
        .post("/api/vehicle/add", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          alert("Vehicle registered"),
            (window.location.href = "/owner/vehicle");
        })
        .catch((error) => console.error("Error registering vehicle:", error));
    },
    fetchUserData() {
      axios
        .get("/api/owner/me")
        .then((response) => {
          this.owner = response.data;
          this.loggedIn = true;
          console.log("Owner data:", this.owner);
        })
        .catch((error) => {
          console.error(error);
          this.$router.push("/owner/login");
        });
    },
    fetchLocations() {
      axios
        .get("/api/location/all")
        .then((response) => {
          this.locations = response.data; // Assuming the response is an array of locations
        })
        .catch((error) => {
          console.error("Error fetching locations:", error);
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