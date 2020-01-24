<template>
  <div>
    <v-container fluid class="background">
      <v-hover v-slot:default="{ hover }" >
        <v-card width="800" :elevation="hover ? 12 : 5" style="margin: auto;" >
          <v-app-bar dark color="blue darken-2" >
            <v-icon large color="white" dark>mdi-comment-plus</v-icon>
            <v-spacer></v-spacer>

            <v-toolbar-title>
              <h1 class="font-weight-bold display-1 basil--text">รีวิวคอร์สเรียน</h1>
            </v-toolbar-title>

            <v-spacer></v-spacer>
            <v-icon large color="white" dark>mdi-message-text</v-icon>
          </v-app-bar>

          <v-container style="margin-top: 30px; padding-bottom: 20px;" >

            <v-row>
              <v-col cols="8" style="margin: auto;">
                <v-select
                id = "coursename001"
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
                id = "rating001"
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
                id= "improvement001"
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
                  id = "comment"
                  :rules="[(v) => !!v || 'จำเป็นต้องกรอกข้อมูล']"
                  required
                  v-model="reviewCourse.comment"
                  outlined
                  label="แสดงความคิดเห็นเพิ่มเติม(ไม่เกิน 80 ตัวอักษร)"
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
    
    <!-- popup Success -->
    <template>
      <v-row justify="center">
        <v-dialog v-model="popup.Success" max-width="500px">
          <v-card style="background-color: #F2F3F4">
            <v-card-title>
              <span class="display-1 font-weight-light">บักทึกข้อมูลสำเร็จ!</span>
              <v-spacer></v-spacer>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <Label>{{popup.TextSuccess}}</Label>
                </v-row>
                <v-row>
                  <v-spacer></v-spacer>
                  <v-btn
                    @click="reloadPage"
                    class="font-weight-light"
                    color="primary"
                    width="100"
                    height="20"
                  >close</v-btn>
                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-dialog>
      </v-row>
    </template>

    <!-- popup Error -->

    <template>
      <v-row justify="center">
        <v-dialog v-model="popup.Error" max-width="500px">
          <v-card style="background-color: #F2F3F4">
            <v-card-title>
              <span class="display-1 font-weight-light">บันทึกไม่สำเร็จ</span>

              <v-spacer></v-spacer>

              <v-btn icon>
                <v-icon size="24px" @click="popup.Error = false">fas fa-times</v-icon>
              </v-btn>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <Label>{{popup.TextError}}</Label>
                </v-row>
                <v-row>
                  <v-spacer></v-spacer>

                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-dialog>
      </v-row>
    </template>

  </div>
</template>


<script>
import http from "../../http-common";
export default {
  name: "AddReviews",
  data: () => ({
    popup: {
      TextSuccess: "TextSuccess",
      Success: false,
      TextError: "TextError",
      Error: false
    },
    reviewCourse: {
      enrollCourse:"",
      rating:"",
      improvement:"",
      comment:"",
      student_id:""
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

          var i = 0;
          var enrollCourses = [];
          var userId = this.reviewCourse.student_id;
          for (let elem in this.enrollCourses) {
            if (this.enrollCourses[elem].student.id == userId) {
              enrollCourses[i] = this.enrollCourses[elem];
              i = i + 1;
            }
          }

          this.enrollCourses = enrollCourses;

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
          this.popup.Success = true;
          this.popup.TextSuccess = "บักทึกข้อมูลเสร็จสิ้น! ขอบคุณที่ให้ความร่วมมือ";
          
        })
        .catch(e => {
          console.log(e);
          this.popup.Error = true;
          this.popup.TextError = "กรุณาเลือกและกรอกข้อมูลให้ครบถ้วน";
        });
    },getStudents() {
      http
        .get("/student/")
        .then(response => {
          this.students = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },setLoginBy() {
      this.reviewCourse.student_id = this.$session.get("userId");
      console.log(this.$session.get("userId"));
    },
    reloadPage() {
      window.location.reload(false);
    }
    
  },
  mounted() {
    this.setLoginBy();
    this.getEnrollCourses();
    this.getRatings();
    this.getImprovements();
  }
};
</script>

<style scoped lang="scss">
.background {
  background-image: url('image1.jpg');
  height: 100%;
  margin: 0px;
  background-size: cover;
  background-attachment: fixed;
}
</style>

