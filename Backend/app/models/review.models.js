const sql = require("./db.js");
const bcrypt = require("bcryptjs");
// constructor for the customer object
const Review = function (review) {
  this.customerID = review.customerID;
  this.sellerID = review.sellerID;
  this.description = review.description;
  this.score = review.score;
};

Review.create = (review, result) => {
  //console.log(temp);
  sql.query("INSERT INTO reviews SET ?", review, (err, res) => {
    if (err) {
      console.log("Error: ", err.code);
      result(err, null);
      return;
    }

    //console.log("created customer: ", { id: res.insertId, ...newCustomer });
    result(null, { ...review });
  });
};

Review.getAll = (result) => {
  let query = "SELECT * FROM reviews";

  sql.query(query, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    //console.log("customers: ", res);
    result(null, res);
  });
};

module.exports = Review;
