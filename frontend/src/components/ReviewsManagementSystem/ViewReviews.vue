<template>
    <div align="center" justify="center">
      <v-card id="card-1" class="card-table">
        
        <v-card-title id="title-card" class="cardBG">
          <label style="margin-bottom: 30px;margin-left: 20px;">ประวัติรีวิวคอร์สเรียนจากผู้เรียน</label>
        </v-card-title>

        <v-data-table
          :headers="headers"
          :items="review"
          :page.sync="page"
          :items-per-page="itemsPerPage"
          hide-default-footer
          class="elevation-1"
          @page-count="pageCount = $event"
          id="table"
        ></v-data-table>
        
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
          { text: 'วันที่รีวิว', value: 'reviewDate', width: '100px' },
          { text: 'ความคิดเห็น', value: 'comment', width: '300px' },
        ],
      
      review:[]
    };
  },
  methods: {
    /* eslint-disable no-console */
    getReviews() {
      http
        .get("/reviewCourse/")
        .then(response => {
          this.review = response.data;
          console.log(this.review);
        })
        .catch(e => {
          console.log(e);
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
    margin: 40px;
    width: 900px;
    border-style: solid;
    border-color: rgb(0, 0, 0);
}
.cardBG {
  background-color: rgb(0, 0, 0);
  color: rgb(255, 255, 255);
}
</style>



