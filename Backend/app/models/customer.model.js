const sql = require("./db.js");
const bcrypt = require("bcryptjs");
// constructor for the customer object
const Customer = function (customer) {
  this.email = customer.email;
  this.password = customer.password;
  this.username = customer.username;
  this.fullname = customer.fullname;
};

Customer.create = (newCustomer, result) => {
  var temp = sql.query("SELECT `email` FROM `customers` WHERE `email` = ?", [
    newCustomer.email,
  ]);
  //console.log(temp);
  sql.query("INSERT INTO customers SET ?", newCustomer, (err, res) => {
    if (err) {
      console.log("Error: ", err.code);
      result(err, null);
      return;
    }

    //console.log("created customer: ", { id: res.insertId, ...newCustomer });
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

Customer.findById = (user, result) => {
  console.log(user);
  sql.query(
    `SELECT * FROM customers WHERE email = "${user.email}"`,
    async (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(err, null);
        return;
      }

      const passMatch = await bcrypt.compare(user.password, res[0].password);
      if (!passMatch) {
        result(err, null);
      }

      if (res.length) {
        console.log("found customer: ", res[0]);
        result(null, res[0]);
        return;
      }

      // not found customer with the id
      // result({ kind: "not_found" }, null);
    }
  );
};
//update a customer by id
Customer.updateById = (id, customer, result) => {
  sql.query(
    "UPDATE customers SET email = ?, fullname = ?, username = ?, password = ? WHERE email = ?",
    [
      customer.email,
      customer.fullname,
      customer.username,
      customer.password,
      id,
    ],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found customer with the id
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated customer: ", { id: id, ...customer });
      result(null, { id: id, ...customer });
    }
  );
};
module.exports = Customer;
