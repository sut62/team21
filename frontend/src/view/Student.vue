<template>
  <v-app>
    <v-app-bar app absolute color="#100E17" dark height="90px">
      <!-- <v-app-bar-nav-icon> </v-app-bar-nav-icon> -->
      <img src="../assets/logo2.png" style="height: 80%;" />
      <!-- <v-toolbar-title style="color: white;" class="display-2 font-weight-black">THE TUTOR</v-toolbar-title> -->
      <v-btn text class="headline font-weight-light" color="white">{{componentName}}</v-btn>
      <Label class="my-2 display-1" style="color: white; margin-left: 10px;">/</Label>
      <v-spacer></v-spacer>

      <div class="my-2" style="margin-right: 0px;">
        <v-btn
          text
          class="headline font-weight-light"
          color="white"
        >NAME: {{this.$session.get('fullname')}}</v-btn>
      </div>
      <div class="my-2 display-2" style="margin-right: 20px;">
        <Label style="color: white ">|</Label>
      </div>

      <v-menu transition="slide-x-transition" bottom right>
        <template v-slot:activator="{ on }">
          <v-btn class="deep-orange headline" color="primary" dark v-on="on">
            <v-icon size="24px" style="margin-right: 10px; color: white; width:30px;">fas fa-bars</v-icon>
            Menu
          </v-btn>
        </template>

        <v-list style="background-color: #1A76D2;">
          <v-list-item @click="logOut">
            <v-list-item-title style="color: white;">
              <v-icon size="24px" style="margin-right: 10px; color: white; width:30px;">fas fa-door-open</v-icon>LOGOUT
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

      <!-- <div class="my-2">
        <v-btn @click="logOut" text class="headline font-weight-light" color="white">LOGOUT</v-btn>
      </div> -->
    </v-app-bar>

    <v-content>
      <template v-if="show.reviews">
        <AddReviews></AddReviews>
      </template>
    </v-content>

    <v-footer class="flex" flat tile style="background-color: #100E17; ">
      <v-card
        flat
        tile
        class="lighten-1 white--text text-center"
        style="background-color: #100E17; margin: auto;"
      >
        <v-card-text style="color: white;">
          <v-btn v-for="icon in icons" :key="icon" class="mx-4 white--text" icon>
            <v-icon size="24px">{{ icon }}</v-icon>
          </v-btn>
        </v-card-text>

        <!-- <v-divider></v-divider> -->

        <v-card-text class="white--text" style="color: white;">
          <strong>Copyright © 2020 | SUT-SE-TEAM21</strong>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-app>
</template>



<script>
import AddReviews from "../components/ReviewsManagementSystem/AddReviews";

export default {
  /* eslint-disable no-console */
  components: {
    AddReviews
  },
  data() {
    return {
      componentName: "",
      show: {
        reviews: false
      },
      icons: [
        "fab fa-facebook",
        "fab fa-twitter",
        "fab fa-google-plus",
        "fab fa-linkedin",
        "fab fa-instagram"
      ]
    };
  },
  methods: {
    /* eslint-disable no-console */
    showReviews() {
      this.componentName = "Reviews Course";
      this.show.reviews = true;
    },
    redirectHome() {
      this.$router.push("/");
    },
    showLogin(x) {
      if (x == "stu") {
        this.dialog.LoginEmp = false;
        this.dialog.LoginStu = true;
      } else {
        this.dialog.LoginStu = false;
        this.dialog.LoginEmp = true;
      }
    },
    redirectEmployee() {
      this.$router.push("/Employee");
    },
    logOut() {
      this.$session.destroy();
      this.$router.push("/");
    }
  },
  beforeCreate() {
    if (!this.$session.exists()) {
      this.$router.push("/");
    }
  },
  mounted() {
    this.showReviews();
  }
};
</script>