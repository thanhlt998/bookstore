$(function() {
  // Validate form
  // ------------------------------------------------------------
  var registerForm = $("#registerForm");

  validatePasswordRegisterForm(registerForm);

  registerForm.submit(validateSubmitRegisterForm);

  // Add cart ------------------------------------------------------------
  $("#add-button").click(increaseQuantity);
  $("#minus-button").click(decreaseQuantity);

  // Replace img ---------------------------------------------------------
  $(".small-img").click(replaceImg);

  // Add cart -----------------------------------------------------------

  $("#shopCart").hover(
    function() {
      $("#cartDetail")
        .stop()
        .slideDown();
    },
    function() {
      $("#cartDetail")
        .stop()
        .slideUp();
    }
  );
});

// --------------------------------------------------------------------------------------------
// Validate Form

function validateSubmitRegisterForm(event) {
  var registerForm = $("#registerForm");
  var passwordField = registerForm.find($("#password"));
  var confirmPasswordField = registerForm.find($("#confirmPassword"));

  validatePasswordField(passwordField, event);
  validateConfirmPasswordField(passwordField, confirmPasswordField, event);
}

function validatePasswordRegisterForm(registerForm) {
  var passwordField = registerForm.find($("#password"));
  var confirmPasswordField = registerForm.find($("#confirmPassword"));

  passwordField.blur(function(event) {
    validatePasswordField(passwordField, event);
  });

  confirmPasswordField.blur(function(event) {
    validateConfirmPasswordField(passwordField, confirmPasswordField, event);
  });
}

function validatePasswordField(passwordField, event) {
  var password = passwordField.val();
  if (!password.length >= 8 || !/.*[0-9].*/.test(password)) {
    $("#password-feedback").text(
      "Must not contain any whitespace characters and contain at least 8 characters."
    );
    event.preventDefault();
  } else {
    $("#password-feedback").text("");
  }
}

function validateConfirmPasswordField(
  passwordField,
  confirmPasswordField,
  event
) {
  var password = passwordField.val();
  var confirmPassword = confirmPasswordField.val();
  if (password !== confirmPassword) {
    $("#confirmPasswordAppend").html(
      "<i class='far fa-times-circle text-danger'></i>"
    );
    event.preventDefault();
  } else {
    $("#confirmPasswordAppend").html(
      "<i class='far fa-check-circle text-success'></i>"
    );
  }
}

function validateUsernameField(event) {
  event.preventDefault();
  var username = $(this).val();
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: event.data.url,
    data: {
      username: username
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      console.log(data);
      if (data === true) {
        $("#usernameAppend").html(
          "<i class='far fa-check-circle text-success'></i>"
        );
        event.preventDefault();
      } else {
        $("#usernameAppend").html(
          "<i class='far fa-times-circle text-danger'></i>"
        );
      }
    },
    error: function(e) {
      console.log(e);
    }
  });
}

// // Register
// function register(event){
// $.ajax({
// type: "GET",
// contentType: "application/json",
// url: event.data.url,
// data: {
// name: $("#name").val(),
// email: $("#email").val(),
// username: $("#username").val(),
// password: $("#password").val(),
// birthDate: $("#birthDate").val(),
// gender: $("#gender").val(),
// address: $("#address").val(),
// phone: $("#phone").val()
// },
// dataType: "json",
// timeout: 10000,
// success: function(data){
// if(data === true){
// $("#dismissRegisterButton").click();
// alert("Register successfully! Please login.");
// }
// else {
// alert("Failed to register.");
// }
// event.preventDefault();
// },
// error: function(error){
// console.log(error);
// }
// })
// }

// ------------------------------------------------------------------------------------------------------------------------

// add cart
function increaseQuantity() {
  var quantityAdd = $("#quantity-add");
  var currentValue = parseInt(quantityAdd.val());
  quantityAdd.val(currentValue + 1);
  $("#add-cart-button").attr("quantity", quantityAdd.val());
}

function decreaseQuantity() {
  var quantityAdd = $("#quantity-add");
  var currentValue = parseInt(quantityAdd.val());
  if (currentValue > 1) {
    quantityAdd.val(currentValue - 1);
  }
  $("#add-cart-button").attr("quantity", quantityAdd.val());
}

function addCart(event) {
  var bookId = $(this).attr("bookid");
  var quantity = $(this).attr("quantity");
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: event.data.url,
    data: {
      bookId: bookId,
      quantity: quantity
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      console.log(data);
      if (data === false) {
        alert("Cannot add more this book.");
      } else {
        console.log(data.cart);
        var cart = data.cart;
        $("#badge").text(data.totalQuantity);

        var tableBody = $("#cartDetail").find("tbody");
        tableBody.empty();
        for (var i = 0; i < cart.length; i++) {
          $("<tr>")
            .append($("<td>").text(cart[i].book.bookName))
            .append($("<td>").text(cart[i].quantity))
            .append(
              $("<td>")
                .addClass("text-danger align-middle")
                .append(
                  $("<i>")
                    .addClass("far fa-times-circle remove-cart-item")
                    .attr("book-id", cart[i].book.bookId)
                )
            )
            .appendTo(tableBody);
        }
        $("<tr>")
          .append($("<td>").text("Total Price"))
          .append(
            $("<td>")
              .attr("colspan", "2")
              .text(data.totalPrice)
          )
          .appendTo(tableBody);
      }
    },
    error: function(error) {
      console.log(error);
    }
  });
}

$(".remove-cart-item").hover(
  function(event) {
    $(this)
      .stop()
      .animate(
        {
          "font-size": "120%"
        },
        300,
        "swing"
      );
  },
  function(event) {
    $(this)
      .stop()
      .animate({
        "font-size": "100%"
      });
  }
);

function removeCartItem(event) {
  var bookId = $(this).attr("book-id");
  var removeIcon = $(this);
  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: event.data.url + "removeCartItem",
    data: {
      bookId: bookId
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      removeIcon
        .parent()
        .parent()
        .remove();
      $("#shopCart")
        .find("td")
        .last()
        .text(data.totalPrice);
      $("#badge").text(data.totalQuantity);
    },
    error: function(error) {
      console.log(error);
    }
  });
}

// confirm order

$("#order-info-button").click(function(event) {
  $(this)
    .parent()
    .parent()
    .find("td")
    .first()
    .text("Additional Information");
  $(this).css("display", "none");
  $(this)
    .parent()
    .find(".info-form")
    .slideDown();
});

function isValidOrderInfo(shipAddress, paymentMethod) {
  var flag = true;
  if (shipAddress === "") {
    $("#ship-address-feedback").text("Please fill in this field");
    flag = false;
  }
  if (paymentMethod === "") {
    $("#payment-method-feedback").text("Please fill in this field");
    flag = false;
  }
  return flag;
}

function createOrder(event) {
  var shipAddress = $("#shipAddress").val();
  var paymentMethod = $("#paymentMethod").val();
  var message = "";
  console.log(shipAddress + " " + paymentMethod);
  if (isValidOrderInfo(shipAddress, paymentMethod)) {
    $.ajax({
      type: "GET",
      async: false,
      contentType: "application/json",
      url: event.data.url + "createOrder",
      data: {
        shipAddress: shipAddress,
        paymentMethod: paymentMethod
      },
      dataType: "json",
      timeout: 10000,
      success: function(data) {
        console.log(data);
        if (data === false) {
          message =
            "Your order is not completed! Please add Cart and order again!";
        } else {
          message = "Your order is ordered successfully!";
        }
      },
      error: function(error) {
        message = "Some errors occured!";
        console.log(error);
      }
    });
  } else {
    console.log("blank content");
  }
  alert(message);
  $(this).prop("disabled", true);
  $(location).attr("href", event.data.url);
}

// small-picture
// --------------------------------------------------------------------------------------------
function replaceImg(event) {
  var mainImg = $("#main-img");
  mainImg.attr("src", $(this).attr("src"));
}

// Profile
// -------------------------------------------------------------------------------------------------

$(".list-group-item").click(function(event) {
  var listItem = [
    $("#view-profile"),
    $("#change-password"),
    $("#order-history"),
    $("#order-detail")
  ];
  $(this)
    .parent()
    .find(".active")
    .toggleClass("active");
  for (var i = 0; i < listItem.length; i++) {
    listItem[i].css("display", "none");
  }
  $(this).addClass("active");

  console.log($(this).attr("data-target"));
  $($(this).attr("data-target"))
    .stop()
    .slideDown(400, "swing");
});

$(".fa-edit").click(function(event) {
  $(this)
    .parent()
    .parent()
    .find(".inputChange")
    .slideToggle(400, "linear");
});

$("#profile")
  .find(".changeField")
  .change(function(event) {
    $("#save-changes-button").prop("disabled", false);
    $(this)
      .parent()
      .parent()
      .find("span")
      .text($(this).val());
  });
