const sql = require("./db.js");
const bcrypt = require("bcryptjs");
// constructor for the customer object
const Seller = function (seller) {
  this.categoryID = seller.categoryID;
  this.password = seller.password;
  this.fullname = seller.fullname;
  this.description = seller.description;
  this.email = seller.email;
};

Seller.create = (seller, result) => {
  var temp = sql.query("SELECT `email` FROM `seller` WHERE `email` = ?", [
    seller.email,
  ]);
  //console.log(temp);
  sql.query("INSERT INTO seller SET ?", seller, (err, res) => {
    if (err) {
      console.log("Error: ", err.code);
      result(err, null);
      return;
    }

    //console.log("created customer: ", { id: res.insertId, ...newCustomer });
    result(null, { ...seller });
  });
};

Seller.getAll = (result) => {
  let query = "SELECT * FROM seller";

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

//find by id
Seller.findById = (user, result) => {
  console.log(user);
  sql.query(
    `SELECT * FROM seller WHERE email = "${user.email}"`,
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

module.exports = Seller;
