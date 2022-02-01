const sql = require("./db.js");

// constructor for the customer object
const Customer = function (customer) {
  this.email = customer.email;
  this.password = customer.password;
  this.username = customer.username;
  this.fullname = customer.fullname;
};

Customer.create = (newCustomer, result) => {
  sql.query("INSERT INTO customers SET ?", newCustomer, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created customer: ", { id: res.insertId, ...newCustomer });
    result(null, { ...newCustomer });
  });
};

Customer.getAll = (result) => {
  let query = "SELECT * FROM customers";

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
module.exports = Customer;
