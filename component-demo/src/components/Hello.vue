<template>
    <div>
        <el-table
        :data="tableData"
        style="width: 100%"
        :row-class-name="tableRowClassName">
        <el-table-column
            prop="date"
            label="Date"
            width="180">
        </el-table-column>
        <el-table-column
            prop="name"
            label="Name"
            width="180">
        </el-table-column>
        <el-table-column
            prop="address"
            label="Address">
        </el-table-column>
        </el-table>

        <el-date-picker
            v-model="value1"
            type="date"
            placeholder="Pick a day">
        </el-date-picker>

        <i class="fa fa-camera-retro"></i>
    </div>
  </template>
  
  <style>
    .el-table .warning-row {
      background: oldlace;
    }
  
    .el-table .success-row {
      background: #f0f9eb;
    }
  </style>
  
  <script>
import axios from 'axios';
    export default {
      methods: {
        tableRowClassName({row, rowIndex}) {
          if (rowIndex === 1) {
            return 'warning-row';
          } else if (rowIndex === 3) {
            return 'success-row';
          }
          return '';
        }
      },
      data() {
        return {
          tableData:  [],
          value1: '',
        }
      },
      created: function(){
        axios.get("/users").then((response) => {
            console.log('response.data: ' + response.data)
            this.tableData = response.data
        })
      },
    }
  </script>