<template >
  <v-container fluid class="background">
    <v-hover v-slot:default="{ hover }" >
      <v-card width="800" :elevation="hover ? 12 : 5" style="margin: auto;" >
        <v-app-bar dark color="light-blue lighten-1" >
          <v-icon large color="green darken-2" dark>mdi-message-text</v-icon>
          <v-spacer></v-spacer>

          <v-toolbar-title>
             <h1 class="font-weight-bold display-1 basil--text">รีวิวคอร์สเรียน</h1>
          </v-toolbar-title>

          <v-spacer></v-spacer>
          <v-icon large color="green darken-2" dark>mdi-message-text</v-icon>
        </v-app-bar>

        <v-container style="margin-top: 50px; padding-bottom: 30px;" >

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select 
              item-text="course.courseName"
              item-value="id"
              v-model="reviewCourse.enrollCourse"
              :items="enrollCourses"
              :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
              required
              label="เลือกคอร์สที่ท่านลงทะเบียนแล้ว เพื่อรีวิว" outlined></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select 
              item-text="ratingType"
              item-value="id"
              v-model="reviewCourse.rating"
              :items="ratings"
              :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
              required
              label="เลือกระดับความพึงพอใจของท่าน" outlined></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select 
              item-text="improvementType"
              item-value="id"
              v-model="reviewCourse.improvement"
              :items="improvements"
              :rules="[(v) => !!v || 'จำเป็นต้องเลือกข้อมูล']"
              required
              label="เลือกประเภทสิ่งที่อยากให้ปรับปรุง" outlined></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col md="8" style="margin: auto;">
              <v-textarea
                :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                required
                v-model="reviewCourse.comment"
                outlined
                label="แสดงความคิดเห็นเพิ่มเติม"
                dense
              ></v-textarea>
            </v-col>

          </v-row>  

          <v-row>
            <v-btn
              style="margin: auto;"
              large
              color="orange darken-2"
              dark
              @click="saveReviewCourse"
            > บันทึกการรีวิว</v-btn>
          </v-row>

          
        </v-container>
      </v-card>
    </v-hover>
  </v-container>
</template>


<script>
import http from "../../http-common";
export default {
  name: "AddReviews",
  data: () => ({
    reviewCourse: {
      enrollCourse:"",
      rating:"",
      improvement:"",
      comment:""
    },
    ratings: [],
    enrollCourses: [],
    improvements: [],
  }),
  /* eslint-disable no-console */
  methods: {
    getEnrollCourses() {
      http
        .get("/enrollCourse/")
        .then(response => {
          this.enrollCourses = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getRatings() {
      http
        .get("/Rating")
        .then(response => {
          this.ratings = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getImprovements() {
      http
        .get("/improvement")
        .then(response => {
          this.improvements = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveReviewCourse() {
      // @PostMapping("/reviewCourse/{enrollCourse_id}/{rating_id}/{improvement_id}/{comment}")
      http
        .post(
          "/reviewCourse/" +
            this.reviewCourse.enrollCourse +
            "/" +
            this.reviewCourse.rating +
            "/" +
            this.reviewCourse.improvement +
            "/" +
            this.reviewCourse.comment,
          this.employee
        )
        .then(response => {
          console.log(response);
          alert("บันทึกสำเร็จ! ขอบคุณสำหรับการรีวิว");
          location.reload();
        })
        .catch(e => {
          console.log(e);
          alert("กรุณาป้อนข้อมูลให้ครบ");
        });
    }
    
  },
  mounted() {
    this.getEnrollCourses();
    this.getRatings();
    this.getImprovements();


  }
};
</script>

<style scoped lang="scss">
.background {
  height: 100%;
  background-image: url('image1.jpg');
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  top: 0;
  left: 0;
}
</style>

