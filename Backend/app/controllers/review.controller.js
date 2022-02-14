const sql = require("../models/db.js");
const Review = require("../models/review.models.js");

// Create and Save a new review
exports.create = async (req, res) => {
  // Validate request
  if (!req.body) {
    return res.status(400).send({
      message: "Content can not be empty!",
    });
  }

  // Create a customer
  const review = new Review({
    customerID: req.body.customerID,
    sellerID: req.body.sellerID,
    description: req.body.description,
    score: req.body.score,
  });

  // Save customer in the database
  Review.create(review, (err, data) => {
    if (err)
      res.status(500).send({
        message: err || "Some error occurred while creating the Customer.",
      });
    else res.status(200).send(data);
  });
};

// Retrieve all customers from the database (with condition).
exports.findAll = (req, res) => {
  Review.getAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving customers.",
      });
    else res.send(data);
  });
};
