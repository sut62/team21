<template>
  <v-container fluid>
    <v-hover v-slot:default="{ hover }">
      <v-card width="600" :elevation="hover ? 12 : 5" style="margin: auto; margin-top: 50px;">
        <v-app-bar dark color="light-blue lighten-1">
          <v-btn icon>
            <v-icon large>mdi-label</v-icon>
          </v-btn>

          <v-toolbar-title>System From</v-toolbar-title>

          <v-spacer></v-spacer>

          <v-btn icon>
            <v-icon>mdi-dialpad</v-icon>
          </v-btn>
        </v-app-bar>

        <v-container style="margin-top: 50px; padding-bottom: 30px;">
          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-text-field label="Outlined" outlined dense></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select :items="items2" label="Outlined style" outlined></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select :items="items3" label="Outlined style" outlined></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="8" style="margin: auto;">
              <v-select :items="items3" label="Outlined style" outlined></v-select>
            </v-col>
          </v-row>

          <v-row>
            <v-btn
              style="margin: auto;"
              large
              color="light-blue lighten-1"
              dark
              @click="snackbar = true"
            > SAVE FROM</v-btn>
          </v-row>
        </v-container>
      </v-card>
    </v-hover>

    <v-row>
      <v-snackbar v-model="snackbar">
        {{ text }}
        <v-btn color="pink" text @click="snackbar = false">Close</v-btn>
      </v-snackbar>
    </v-row>
  </v-container>
</template>
<script>
export default {
  name: "AddStudent",
  data: () => ({
    items1: ["Foo", "Bar", "Fizz", "Buzz"],
    items2: ["Foo", "Bar", "Fizz", "Buzz"],
    items3: ["Foo", "Bar", "Fizz", "Buzz"],
    snackbar: false,
    text: "OK !,Data has been saved successfully."
  })
};
</script>

