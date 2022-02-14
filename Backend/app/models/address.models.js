const sql = require("./db.js");
const bcrypt = require("bcryptjs");
const { add } = require("nodemon/lib/rules");
// constructor for the customer object
const Address = function (address) {
  this.streetName = address.streetName;
  this.streetNumber = address.streetNumber;
  this.province = address.province;
  this.country = address.country;
  this.postalcode = address.postalcode;
  this.sellerID = address.sellerID || null;
  this.customerID = address.customerID || null;
};

Address.create = (address, result) => {
  //console.log(temp);
  sql.query("INSERT INTO address SET ?", address, (err, res) => {
    if (err) {
      console.log("Error: ", err.code);
      result(err, null);
      return;
    }

    //console.log("created customer: ", { id: res.insertId, ...newCustomer });
    result(null, { ...address });
  });
};

module.exports = Address;
