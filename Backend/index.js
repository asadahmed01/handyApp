const express = require("express");
// const bodyParser = require("body-parser"); /* deprecated */
const cors = require("cors");
const routes = require("./app/routes/customer.routes.js");
const app = express();

app.use(cors());
app.options("*", cors());

// parse requests of content-type - application/json
app.use(express.json()); /* bodyParser.json() is deprecated */

// parse requests of content-type - application/x-www-form-urlencoded
app.use(
  express.urlencoded({ extended: true })
); /* bodyParser.urlencoded() is deprecated */

// simple route
app.get("/", (req, res) => {
  res.json({ message: "Welcome to Handy App application." });
});
app.use("/customers", require("./app/routes/customer.routes"));
//require("./app/routes/customer.routes.js")(app);

// set port, listen for requests
const PORT = process.env.PORT || 3001;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});
