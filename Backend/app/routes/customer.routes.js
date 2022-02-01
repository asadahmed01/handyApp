const router = require("express").Router();
const { findAll, create } = require("../controllers/customer.controller.js");
router.get("/getAll", findAll);
router.post("/createNew", create);
module.exports = router;
