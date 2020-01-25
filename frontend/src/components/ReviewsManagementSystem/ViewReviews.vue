<template>
    <div align="center" justify="center">
      <v-card id="card-1" class="card-table">
        
        <v-card-title id="title-card" class="cardBG">
          <label style="margin-bottom: 30px;margin-left: 20px;">ประวัติรีวิวคอร์สเรียนจากผู้เรียน</label>
        </v-card-title>

        <v-data-table
          id="table"
          :headers="headers"
          :items="review"
          :page.sync="page"
          :items-per-page="itemsPerPage"
          hide-default-footer
          class="elevation-1"
          v-if="view.loading"
          loading loading-text="กำลังโหลดข้อมูล รอสักครู่. . . "
        >
        </v-data-table>

        <v-data-table
          id="table2"
          :headers="headers"
          :items="review"
          :page.sync="page"
          :items-per-page="itemsPerPage"
          hide-default-footer
          class="elevation-1"
          @page-count="pageCount = $event"
          v-else-if="view.data"
        >
        </v-data-table>
        
        
        <div class="text-center pt-2">
          <v-pagination v-model="page" :length="pageCount"></v-pagination>
        </div>

      </v-card>

    </div>
</template>

<script>
import http from "../../http-common";
export default {
  name: "ViewReviews",
  data() {
    return {
      view: {
        data: false,
        loading: false
      },
      page: 1,
      pageCount: 0,
      itemsPerPage: 10,
      headers: [
        {
          text: 'ลำดับที่',
          align: 'left',
          sortable: false,
          value: 'id',
        },
        { text: 'ชื่อคอร์สเรียน', value: 'enrollCourse.course.courseName', width: '110px' },
        { text: 'ระดับความพึงพอใจ', value: 'rating.ratingType', width: '140px'},
        { text: 'ประเภทที่อยากให้ปรับปรุง', value: 'improvement.improvementType' ,width: '170px'},
        { text: 'วันที่รีวิว', value: 'reviewDate', width: '150px' },
        { text: 'ความคิดเห็น', value: 'comment', width: '300px' },
      ],
      review:[]
    };
  },
  methods: {
    /* eslint-disable no-console */
    getReviews() {
      this.view.data = false;
      this.view.loading = true;
      http
        .get("/reviewCourse/")
        .then(response => {
          this.review = response.data;
          console.log(this.review);
          this.view.loading = false;
          this.view.data = true;
        })
        .catch(e => {
          console.log(e);
          this.view.loading = false;
          this.view.data = true;
        });
    }
  },
  mounted() {
    this.getReviews();
  }
};
</script>

<style scoped lang="scss">
.card-table {
    width: 950px;
    border-style: solid;
    border-color: rgb(0, 0, 0);
}
.cardBG {
  background-color: rgb(0, 0, 0);
  color: rgb(255, 255, 255);
}
</style>



