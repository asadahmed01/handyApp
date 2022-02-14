const { findAll, create } = require("../controllers/review.controller");

const router = require("express").Router();

router.post("/createReview", create);
router.get("/allreviews", findAll);
module.exports = router;
