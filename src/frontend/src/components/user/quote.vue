<template>
  <div v-if="loggedIn">
    <navbar />
    <section></section>
    <form
      @submit.prevent="confirmBooking"
      style="margin: 5rem; border: 1px solid #cecaca; padding: 3rem"
    >
      <h2>Personal Details</h2>
      <table class="form-table">
        <tr>
          <td>
            <label>Full Name:</label>&nbsp;
            <input type="text" v-model="user.name" />
          </td>&nbsp;
          <td>
            <label>Contact No:</label>&nbsp;
            <input type="text" v-model="user.contact" />
          </td>&nbsp;
          <td>
            <label>Address:</label>&nbsp;
            <input type="text" v-model="user.address" />
          </td>
        </tr>
        <!-- <tr>
          <td colspan="3"><h3>Driver License</h3></td>
        </tr> -->
        <!-- <tr>
          <td>
            <input type="file" name="driving_license" required />

            <input type="file" name="driving_license" />
            <img alt="" src="" width="300" height="200" />
          </td>
        </tr> -->
      </table>
      <hr />
      <br />
      <h2>Booking Details</h2>

      <table class="form-table">
        <tr>
          <td>
            <label>Vehicle Location:</label>
            <input
              type="text"
              v-model="vehicleDetails.availablelocation"
              disabled
            />
          </td>
        </tr>
        <br />
        <tr>
          <td>
            <label>From Date:</label>
            <input type="date" :value="pickUpDate" disabled />
          </td>
          <td>
            <label>To Date:</label>
            <input type="date" :value="returnDate" disabled />
          </td>
        </tr>
        <hr />
        <br />
        <tr>
          <td colspan="2"><h3>Vehicle Details</h3></td>
        </tr>
        <tr>
          <td>
            <img :src="`http://localhost:3000/api/images/${vehicleDetails.carimg}`" width="300" height="200" />
          </td>
          <td>
            <table class="fancy-table">
              <tr>
                <td class="odd"></td>
              </tr>
              <tr>&nbsp;
                <th class="odd">Vehicle Model</th>
                <td class="odd">
                  <input
                    type="text"
                    style="border: none"
                    v-model="vehicleDetails.model"
                  />
                </td>
              </tr>
              <tr>&nbsp;
                <th class="even">Manufacturer</th>
                <td class="even">{{ vehicleDetails.manufacturer }}</td>
              </tr>
              <tr>&nbsp;
                <th class="odd">Vehicle Owner</th>
                <td class="odd">
                  &nbsp; <input
                    type="text"
                    style="border: none"
                    v-model="vehicleDetails.owners.fullname"
                  />
                </td>
              </tr>

              <tr>&nbsp;
                <th class="even">Fare</th>
                <td class="even">{{ vehicleDetails.dailyfare }}</td>
              </tr>

              <tr>&nbsp;
                <th class="odd">Booked Days</th>
                <td class="odd">
                  <span style="font-size: 1rem">{{ bookedDays }}</span>
                </td>
              </tr>
              <tr>&nbsp;
                <th class="even">Total Fare</th>
                <td class="even">
                  <span style="font-size: 1rem">{{
                    bookedDays * vehicleDetails.dailyfare
                  }}</span>
                </td>
              </tr>
              <tr>&nbsp;
                <th class="even">Pay By</th>
                <td class="even">
                  <input type="radio" checked />
                  Cash
                </td>
              </tr>
              <tr>
                <th>&nbsp;</th>
                <td>
                  <input
                    type="submit"
                    value="Confirm Booking"
                    style="width: 100%"
                  />
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import Navbar from "../Navbar.vue";
export default {
  components: { Navbar },
  name: "quotePage",
  data() {
    return {
      user: {},
      loggedIn: false,
      numberOfDays: 1,

      vehicleId: null,
      vehicleDetails: null,
      pickUpDate: "",
      returnDate: "",
      bookedDays: 0,
    };
  },
  created() {
    console.log("Initial pickUpDate:", this.pickUpDate);
    console.log("Initial returnDate:", this.returnDate);

    this.fetchUserData();
    this.fetchVehicleDetails();
    this.calculateBookedDays();
  },

  methods: {
    fetchVehicleDetails() {
      const vehicleId = this.$route.query.vehicleId;
      console.log("Vehicle ID:", vehicleId);
      this.pickUpDate = this.$route.query.pickUpDate || "";
      this.returnDate = this.$route.query.returnDate || "";
      if (vehicleId) {
        axios
          .get(`/api/vehicle/${vehicleId}`)
          .then((response) => {
            console.log("API Response:", response.data);
            this.vehicleDetails = response.data;
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },
    calculateBookedDays() {
      if (this.pickUpDate && this.returnDate) {
        const fromDate = new Date(this.pickUpDate);
        const toDate = new Date(this.returnDate);
        const timeDiff = toDate.getTime() - fromDate.getTime();
        const diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
        this.bookedDays = diffDays;
      }
    },

    fetchUserData() {
      axios
        .get("/api/user/me")
        .then((response) => {
          this.user = response.data;
          this.loggedIn = true;
        })
        .catch((error) => {
          console.error(error);
          this.$router.push("/user/login");
        });
    },
    confirmBooking() {
      if (
        this.loggedIn &&
        this.vehicleDetails &&
        this.bookedDays &&
        this.vehicleDetails.dailyfare // Ensure dailyfare is available
      ) {
        console.log("Booking Data:", {
          fromdate: this.pickUpDate,
          todate: this.returnDate,
          booked_days: this.bookedDays,
          total_fare: this.vehicleDetails.dailyfare,
          vehicle_id: this.vehicleDetails.vehicle_id,
          owners_id: this.vehicleDetails.owners.owners_id,
          user_id: this.user.user_id,
        });

        console.log("vehicleDetails", this.vehicleDetails);
        console.log("ownerID", this.vehicleDetails.owners.owners_id);

        console.log("fromDate", this.pickUpDate);
        console.log("toDate", this.returnDate);
        const bookingData = {
          fromDate: this.pickUpDate,
          toDate: this.returnDate,
          booked_days: this.bookedDays,
          total_fare: this.vehicleDetails.dailyfare,
          vehicle_id: this.vehicleDetails.vehicle_id,
          owners_id: this.vehicleDetails.owners.owners_id,
          user_id: this.user.user_id,
        };
        console.log("fromDate", this.pickUpDate);
        console.log("toDate", this.returnDate);
        console.log("Booking Data:", bookingData);

        axios
          .post("/api/bookings/confirm", bookingData)
          .then((response) => {
            console.log("Response fromedate:", bookingData);
            console.log("Booking confirmed:", response.data);

            alert("Booking confirmed!");
            // Optionally, you can redirect the user to a success page after successful booking
            this.$router.push("/user/dashboard");
          })
          .catch((error) => {
            console.error("Error confirming booking:", error);
            alert("Booking confirmation failed.");
          });
      } else {
        alert(
          "Please provide all required details before confirming the booking."
        );
      }
    },
  },
};
</script>

