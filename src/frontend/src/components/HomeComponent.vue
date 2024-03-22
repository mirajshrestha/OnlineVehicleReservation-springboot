<template>
  <Navbar />
  <section class="home" id="home">
    <div class="text">
      <h1><span>Looking</span> to <br />rent a car</h1>

      <div class="app-stores"></div>
    </div>
    <div class="form-container">
      <form @submit.prevent="searchVehicles">
        <div class="input-box">
          <span>Location</span>
          <select v-model="location" name="location">
            <option value="">---Select Location---</option>
            <option value="Kathmandu">Kathmandu</option>
            <option value="Lalitpur">Lalitpur</option>
            <option value="Bhaktapur">Bhaktapur</option>
            <option value="Makawanpur">Makawanpur</option>
          </select>
        </div>
        <div class="input-box">
          <span>Pick-up Date</span>
          <input
            type="date"
            name="pickUpDate"
            v-model="pickUpDate"
            id="pickDate"
          />
        </div>
        <div class="input-box">
          <span>Return Date</span>
          <input
            type="date"
            name="returnDate"
            v-model="returnDate"
            id="retDate"
          />
        </div>
        <input type="submit" value="Submit" class="btn" />
      </form>
    </div>
  </section>

  <!-- Ride Section -->
  <section class="ride" id="ride">
    <div class="heading">
      <span>How It Works</span>
      <h1>Rent With 3 Easy Steps</h1>
    </div>
    <div class="ride-container">
      <div class="box">
        <i class="bx bxs-map"></i>
        <h2>Choose A Location</h2>
        <p></p>
      </div>
      <div class="box">
        <i class="bx bxs-calendar-check"></i>
        <h2>Pick A Date</h2>
        <p></p>
      </div>
      <div class="box">
        <i class="bx bxs-calendar-star"></i>
        <h2>Book A Car</h2>
        <p></p>
      </div>
    </div>
  </section>

  <!-- Algorithm section -->
  <section class="services" v-if="userLog && vehicles.length > 0">
    <div class="heading">
      <span>Nearby Vehicles</span>
    </div>
    <div class="services-container">
      <div
        class="box"
        v-for="(vehicle, index) in vehicles.slice(0, 6)"
        :key="index"
      >
        <div class="box-img">
          <img :src="`http://localhost:3000/api/images/${vehicle[5]}`" alt="" />
        </div>
        <p></p>
        <h3>{{ vehicle[2] }}</h3>
        <h2>{{ vehicle[4] }}</h2>
        <h2>Rs. {{ vehicle[6] }} <span>/day</span></h2>
        <router-link
          :to="{
            path: '/quote',
            query: {
              vehicleId: vehicle.vehicle_id,
              pickUpDate: pickUpDate,
              returnDate: returnDate,
            },
          }"
          class="btn"
        >
          Rent Now
        </router-link>
      </div>
    </div>
  </section>
  <!-- Algorithm section -->

  <!-- Services -->
  <section class="services" id="services">
    <div class="heading">
      <span>Featured Vehicles</span>
    </div>
    <div class="services-container">
      <div
        v-for="(vehicle, index) in featuredVehicles"
        :key="index"
        class="box"
      >
        <div class="box-img">
          <img
            :src="`http://localhost:3000/api/images/${vehicle.carimg}`"
            alt=""
          />
        </div>
        <p></p>
        <h3>{{ vehicle.model }}</h3>
        <h2>{{ vehicle.availablelocation }}</h2>

        <h2>Rs. {{ vehicle.dailyfare }} <span>/day</span></h2>
        <a href="#" class="btn">Rent Now</a>
      </div>
    </div>
  </section>

  <!-- About Section -->
  <section class="about" id="about">
    <div class="heading">
      <span>About Us</span>
      <h1>Best Customer Expericence</h1>
    </div>
    <div class="about-container">
      <div class="about-img">
        <img src="../../public/assets/images/about.png" />
      </div>

      <div class="about-text">
        <span>About Us</span>
        <p>
          At Online Vehicle Reservation, we transcend the conventional
          boundaries of vehicle rental services, offering not just cars but an
          immersive experience tailored to meet your every need. Our commitment
          to delivering the best customer experience is at the heart of
          everything we do. From the moment you engage with our platform, we
          embark on a journey with you, ensuring seamless and memorable rides
          that go beyond the ordinary.
        </p>
        <p>
          <strong> Unparalleled Service, Unmatched Convenience</strong>
        </p>
        <a href="#" class="btn">Learn More</a>
      </div>
    </div>
  </section>

  <!-- Reviews -->
  <!-- <section class="reviews" id="reviews">
    <div class="heading">
      <span>Reviews</span>
      <h1>What our Customers Saying</h1>
    </div>
    <div class="reviews-container">
      <div class="box">
        <div class="rev-img">
          <img src="../../public/assets/images/rev1.jpg" alt="" />
        </div>
        <h2>Someone Name</h2>
        <div class="stars">
          <i class="bx bxs-star"></i>
          <i class="bx bxs-star"></i>
          <i class="bx bxs-star"></i>
          <i class="bx bxs-star"></i>
          <i class="bx bxs-star-half"></i>
        </div>
        <p>
          Lorem ipsum dolor, sit amet consectetur adipisicing elit. Nemo tempore
          distinctio dicta, est molestiae error quis sint magnam.
        </p>
      </div>
      <div class="box">
        <div class="rev-img">
          <img src="../../public/assets/images/rev2.jpg" alt="" />
        </div>
        <h2>Someone Name</h2>
        <div class="stars">
          <i class="bx bxs-star"></i>
          <i class="bx bxs-star"></i>
          <i class="bx bxs-star"></i>
          <i class="bx bxs-star"></i>
          <i class="bx bxs-star-half"></i>
        </div>
        <p>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Soluta,
          laborum quod veniam quasi quam sequi delectus ut facere.
        </p>
      </div>
      <div class="box">
        <div class="rev-img">
          <img src="../../public/assets/images/rev3.jpg" alt="" />
        </div>
        <h2>Someone Name</h2>
        <div class="stars">
          <i class="bx bxs-star"></i>
          <i class="bx bxs-star"></i>
          <i class="bx bxs-star"></i>
          <i class="bx bxs-star"></i>
          <i class="bx bxs-star-half"></i>
        </div>
        <p>
          Lorem ipsum dolor sit amet consectetur, adipisicing elit. Facilis
          dolores vero beatae accusamus! Aliquam distinctio consectetur
          molestias eos?
        </p>
      </div>
    </div>
  </section> -->

  <div class="copyright">
    <p>&#169; OnlineVehicleReservation All Right Reserved</p>
  </div>
</template>
<script>
import Navbar from "./Navbar.vue";
import axios from "axios";
export default {
  name: "HomeComponent",
  components: {
    // HelloWorld,
    // Owners,
    Navbar,
    // HomeComponent
  },
  data() {
    return {
      location: "",
      pickUpDate: "",
      returnDate: "",
      user: {},
      userLog: false,
      location1: null,
      location2: null,
      vehicles: [],
      featuredVehicles: [],
    };
  },
  created() {
    this.fetchUserData();
    this.fetchVehicles();
    this.fetchFeaturedVehicles();
  },
  mounted() {
    //Get user's geolocation
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          this.location1 = {
            latitude: position.coords.latitude,
            longitude: position.coords.longitude,
          };

          console.log("Location1:", this.location1);
        },
        (error) => {
          console.error("Error getting geolocation:", error);
          // Handle the error if needed
        }
      );
    } else {
      console.error("Geolocation not supported");
      // Handle the case where geolocation is not supported
    }

    var date = new Date();
    var tdate = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getUTCFullYear();

    if (tdate < 10) {
      tdate = "0" + tdate;
    }
    if (month < 10) {
      month = "0" + month;
    }

    var minDate = year + "-" + month + "-" + tdate;

    document.getElementById("pickDate").setAttribute("min", minDate);

    document.getElementById("pickDate").addEventListener("input", function () {
      var selectedDate = new Date(document.getElementById("pickDate").value);
      var year = selectedDate.getFullYear();
      var month = (selectedDate.getMonth() + 1).toString().padStart(2, "0");
      var day = selectedDate.getDate().toString().padStart(2, "0");
      var minDate = year + "-" + month + "-" + day;
      document.getElementById("retDate").setAttribute("min", minDate);
    });
  },
  methods: {
    async fetchVehicles() {
      try {
        const response = await axios.get("/api/vehicle/locations");
        this.vehicles = response.data;
        if (this.vehicles.length > 0) {
          const [latitude, longitude] = [
            this.vehicles[0][12],
            this.vehicles[0][14],
          ];
          this.location2 = { latitude, longitude };
          console.log("Location2: ", this.location2);
          this.calculateDistances();
        }
      } catch (error) {
        console.error(error);
      }
      // .then((response) => {
      //   this.vehicles = response.data;
      //   console.log("Vehicles:", this.vehicles);

      //   if (this.vehicles.length > 0) {
      //     const [latitude, longitude] = [
      //       this.vehicles[0][11],
      //       this.vehicles[0][13],
      //     ];
      //     this.location2 = { latitude, longitude };
      //     console.log("Location2: ", this.location2);

      //     this.calculateDistances();
      //   }
      // })
      // .catch((error) => {
      //   console.error(error);
      // });
    },

    calculateDistances() {
      if (this.location1 && this.location2) {
        this.vehicles.forEach((vehicle) => {
          const vehicleLocation = {
            latitude: vehicle[12],
            longitude: vehicle[14],
          };
          vehicle.distance = this.calculateDistance(
            this.location1,
            vehicleLocation
          );
        });
        this.vehicles.sort((a, b) => a.distance - b.distance);
        console.log("Vehicles sorted by distance:", this.vehicles);
      }
    },
    fetchLocationFromRoute(route) {
      const { latitude, longitude } = route.query;
      if (latitude && longitude) {
        this.location1 = {
          latitude: parseFloat(latitude),
          longitude: parseFloat(longitude),
        };
      }
    },
    calculateDistance(location1, location2) {
      console.log("Initiated");
      console.log(location1, location2);
      const { latitude: lat1, longitude: lon1 } = location1;
      const { latitude: lat2, longitude: lon2 } = location2;
      const earthRadius = 6371;

      const degToRad = (degrees) => {
        return degrees * (Math.PI / 180);
      };

      const dLat = degToRad(lat2 - lat1);
      const dLon = degToRad(lon2 - lon1);

      const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(degToRad(lat1)) *
          Math.cos(degToRad(lat2)) *
          Math.sin(dLon / 2) *
          Math.sin(dLon / 2);
      const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
      const distance = earthRadius * c;
      console.log("Distance", distance);
      return distance;
    },
    async fetchFeaturedVehicles() {
      try {
        const response = await axios.get("/api/vehicle/all");
        // Filter vehicles that have 'Accepted' in their feature column
        this.featuredVehicles = response.data.filter(
          (vehicle) => vehicle.feature === "Accepted"
        );
      } catch (error) {
        console.error(error);
      }
    },
    searchVehicles() {
      this.$router.push({
        path: "/search",
        query: {
          location: this.location,
          pickUpDate: this.pickUpDate,
          returnDate: this.returnDate,
        },
      });
    },
    fetchUserData() {
      axios
        .get("/api/user/me")
        .then((response) => {
          this.user = response.data;
          this.userLog = true;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
