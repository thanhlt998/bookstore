$(document).ready(function() {
  // Validate form
  // ------------------------------------------------------------
  // var contextPath = $("#context-path").attr("context-path");
  var registerForm = $("#registerForm");

  validatePasswordRegisterForm(registerForm);

  registerForm.submit(validateSubmitRegisterForm);

  $("#registerForm")
    .find($("#username"))
    .blur(validateUsernameField);

  // Add cart ------------------------------------------------------------
  $("#add-button").click(increaseQuantity);
  $("#minus-button").click(decreaseQuantity);

  // Replace img ---------------------------------------------------------
  $(".small-img").click(replaceImg);

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

  $("#cartDetail")
    .on(
      "hover",
      ".remove-cart-item",
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
    )
    .on("click", ".remove-cart-item", removeCartItem);

  //cart
  $(".add-cart").click(addCart);

  $(".fa-edit").click(editChange);

  //Order
  $("#order-info-button").click(clickOrderInfoButton);

  $("#confirm-order-button").click(createOrder);

  //Profile
  $(".list-group-item").click(changeUserFunction);

  $("#save-changes-button").click(updateUser);

  $("#profile")
    .on("change", ".changeField", changeInformation)
    .change();

  $("#view-order-history-button").click(viewOrderHistory);

  enableChangePasswordValidate($("#change-password"));

  $("#view-order-button").click(viewOrderDetail);

  $("#view-all-users").on("click", ".fa-user-edit", editUserButtonClick);

  $("#view-all-users").on("click", ".fa-save", saveRoleChange);

  $("#view-all-users").on(
    "click",
    "#previous-button, #next-button",
    viewUsersButtonClick
  );

  $("#search-fields").on("change", "#user-id, #username, #name, #authority", searchFieldsOnChange);
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
      "Must not contain any whitespace characters and contain at least 8 characters include at least a number."
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
  var contextPath = $(this).attr("context-path");
  var username = $(this).val();
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/checkAvailableUsername",
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
  var contextPath = $(this).attr("context-path");
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/addCart",
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
        $("#cartDetail").css("height", "");
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
                    .attr("context-path", contextPath)
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

function removeCartItem(event) {
  var bookId = $(this).attr("book-id");
  var removeIcon = $(this);
  var contextPath = $(this).attr("context-path");
  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/removeCartItem",
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
      $("#cartDetail").css("height", "");
    },
    error: function(error) {
      console.log(error);
    }
  });
}

// confirm order

function clickOrderInfoButton(event) {
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
}

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
  var contextPath = $(this).attr("context-path");
  var message = "";
  console.log(shipAddress + " " + paymentMethod);
  if (isValidOrderInfo(shipAddress, paymentMethod)) {
    $.ajax({
      type: "GET",
      async: false,
      contentType: "application/json",
      url: contextPath + "/createOrder",
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

function changeUserFunction(event) {
  var sibling = $(this).siblings(".active");
  sibling.removeClass("active");
  $(sibling.attr("data-target")).css("display", "none");

  $(this).addClass("active");

  console.log($(this).attr("data-target"));
  $($(this).attr("data-target"))
    .stop()
    .slideDown(400, "swing");
}

function editChange(event) {
  $(this)
    .parent()
    .parent()
    .find(".inputChange")
    .slideToggle(400, "linear");
}

function changeInformation(event) {
  var changeField = $(this);
  $("#save-changes-button").prop("disabled", false);
  changeField
    .parent()
    .parent()
    .find("span")
    .text(changeField.val());
}

//profile
function updateUser(event) {
  var contextPath = $(this).attr("context-path");
  var tableProfile = $("#table-profile");
  var name = tableProfile.find("#name").val();
  var email = tableProfile.find("#email").val();
  var birthDate = tableProfile.find("#birthDate").val();
  var gender = tableProfile.find("#gender").val();
  var address = tableProfile.find("#address").val();
  var phone = tableProfile.find("#phone").val();
  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/updateProfile",
    data: {
      name: name,
      email: email,
      birthDate: birthDate,
      gender: gender,
      address: address,
      phone: phone
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      if (data === true) {
        tableProfile
          .find("#table-profile-feedback")
          .text("Your profile is updated!");
        $(this).attr("disabled", true);
      }
    },
    error: function(error) {
      tableProfile
        .find("#table-profile-feedback")
        .text("Your profile cannot be changed!");
    }
  });
}

function viewOrderHistory(event) {
  var historyTableBody = $("#order-history").find("tbody");
  var contextPath = $(this).attr("context-path");
  var page = $(this).attr("page");
  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/viewOrderHistory",
    data: {
      page: page
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append($("<td>").text(data[i].orderId))
          .append($("<td>").text(dateFormat(data[i].orderDate)))
          .append($("<td>").text(dateFormat(data[i].shipDate)))
          .append($("<td>").text(data[i].shipAddress))
          .append($("<td>").text(data[i].totalAmount))
          .append($("<td>").text(data[i].paymentMethod))
          .append($("<td>").text(data[i].status))
          .appendTo(historyTableBody);
      }

      if (data.length < 10) {
        $(this).attr("disabled", true);
      } else {
        $(this).text("View More");
        $(this).attr("page", page + 1);
      }
    },
    error: function(error) {
      console.log(error);
    }
  });
}

function dateFormat(milliseconds) {
  var date = new Date(milliseconds);
  return (
    date.getFullYear() +
    "-" +
    (date.getMonth() < 10 ? "0" + date.getMonth() : date.getMonth()) +
    "-" +
    date.getDate()
  );
}

// validate change password

function validateNewPassword(event) {
  var password = $(this).val();
  if (validNewPassword(password)) {
    $("#new-password-feedback").text("");
  } else {
    $("#new-password-feedback").text(
      "Password must not contain any whitespace and at least 8 characters (include number)"
    );
  }
}

function validNewPassword(password) {
  return password.length >= 8 && /.*[0-9].*/.test(password);
}

function validConfirmNewPassword(event) {
  var confirmNewPassword = $(this).val();
  var newPassword = event.data.newPassword.val();
  if (confirmNewPassword === newPassword) {
    $("#confirm-new-password-feedback").text("");
  } else {
    $("#confirm-new-password-feedback").text(
      "This field must match new password field."
    );
  }
}

function changePasswordFunction(event) {
  var oldPassword = event.data.oldPassword.val();
  var newPassword = event.data.newPassword.val();
  var confirmNewPassword = event.data.confirmNewPassword.val();
  var url = $(this).attr("context-path");
  if (newPassword !== confirmNewPassword) {
    event.preventDefault();
  } else {
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: url + "/changePassword",
      data: {
        newPassword: newPassword,
        oldPassword: oldPassword
      },
      dataType: "json",
      timeout: 10000,
      success: function(data) {
        if (data === true) {
          $("#change-password-feedback").text("Your password is updated!");
        } else {
          $("#old-password-feedback").text("Old password isn't correct!");
          $("#new-password").empty();
          $("confirm-new-password").empty();
          $("#change-password-feedback").text("Fail to update your password!");
        }
      },
      error: function(error) {
        console.log(error);
        $("#change-password-feedback").text("Fail to update your password!");
      }
    });
  }
}

function enableChangePasswordValidate(changePassword) {
  var oldPassword = changePassword.find("#old-password");
  var newPassword = changePassword.find("#new-password");
  var confirmNewPassword = changePassword.find("#confirm-new-password");
  var button = changePassword.find("#change-password-button");

  newPassword.blur(validateNewPassword);
  confirmNewPassword.blur(
    {
      newPassword
    },
    validConfirmNewPassword
  );
  button.click(
    {
      oldPassword: oldPassword,
      newPassword: newPassword,
      confirmNewPassword: confirmNewPassword
    },
    changePasswordFunction
  );
}

function viewOrderDetail(event) {
  var orderDetail = $("#order-detail");
  var orderId = orderDetail.find("#order-id-input").val();
  var contextPath = $(this).attr("context-path");
  var orderDetailView = orderDetail.find("#order-detail-view");
  var tableBody = orderDetail.find("tbody");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/viewOrderDetail",
    data: {
      orderId: orderId
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      if (data === false) {
        orderDetailView.css("display", "none");
        orderDetail
          .find("#order-detail-feedback")
          .text(`No Order with orderId = ${orderId} found.`);
      } else {
        var user = data.user;
        var order = data.order;
        var ordersDetailItemList = data.ordersDetailItemList;
        orderDetailView.find(".name").text(user.name);
        orderDetailView.find(".email").text(user.email);
        orderDetailView.find(".phone").text(user.phone);
        orderDetailView.find(".address").text(order.shipAddress);
        orderDetailView.find(".paymentMethod").text(order.paymentMethod);
        orderDetailView.find(".shipDate").text(dateFormat(order.shipDate));

        tableBody.empty();
        for (var i = 0; i < ordersDetailItemList.length; i++) {
          $("<tr>")
            .append($("<td>").text(i + 1))
            .append($("<td>").text(ordersDetailItemList[i].bookName))
            .append(
              $("<td>").text(ordersDetailItemList[i].ordersDetail.quantity)
            )
            .append(
              $("<td>").text(ordersDetailItemList[i].ordersDetail.pricePerUnit)
            )
            .append(
              $("<td>").text(
                ordersDetailItemList[i].ordersDetail.quantity *
                  ordersDetailItemList[i].ordersDetail.pricePerUnit
              )
            )
            .appendTo(tableBody);
        }
        $("<tr>")
          .append(
            $("<td>")
              .attr("colspan", 4)
              .text("Total Price")
          )
          .append($("<td>").text(order.totalAmount))
          .appendTo(tableBody);
        orderDetailView.fadeIn();
      }
    }
  });
}

function editUserButtonClick(event) {
  var contextPath = $(this).attr("context-path");
  $(this)
    .parent()
    .parent()
    .find(".authority")
    .attr("disabled", false);
  $(this).replaceWith(
    $("<i>")
      .addClass("far fa-save")
      .attr("context-path", contextPath)
  );
}

function saveRoleChange(event) {
  var editIcon = $(this);
  var trParent = $(this)
    .parent()
    .parent();
  var newRole = trParent.find(".authority").val();
  var contextPath = $(this).attr("context-path");
  var userId = trParent
    .find("td")
    .first()
    .text();

  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/changeUserRole",
    data: {
      userId: userId,
      newRole: newRole
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      if (data === true) {
        editIcon.replaceWith(
          $("<i>")
            .addClass("fas fa-user-edit")
            .attr("context-path", contextPath)
        );
        trParent.find(".authority").attr("disabled", true);
        alert(`Change the role of user with userId = ${userId} successfully!`);
      } else {
        alert("Can't change the role of this user!");
      }
    },
    error: function(error) {
      console.log(error);
      alert("Can't change the role of this user!");
    }
  });
}

function viewUsersButtonClick(event) {
  var typeButton = $(this).attr("type-button");
  var page = parseInt($(this).attr("page"));
  var contextPath = $(this).attr("context-path");
  var tableBody = $("#view-all-users").find("tbody");
  var userId = $("#user-id").val();
  var username = $("#username").val();
  var name = $("#name").val();
  var authority = $("#authority").val();
  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/searchUsers",
    data: {
      page: page,
      userId: userId,
      username: username,
      name: name,
      authority: authority
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      console.log(data);
      tableBody.empty();
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].userId)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].username)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].name)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].email)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(dateFormat(data[i].birthDate))
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].gender)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].address)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].phone)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .append(createRoleSelect(data[i].authority))
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .append(
                $("<i>")
                  .addClass("fas fa-user-edit align-middle")
                  .attr("context-path", contextPath)
                  .attr("disabled", true)
              )
          )
          .appendTo(tableBody);
      }

      var previousButton = $("#previous-button");
      var nextButton = $("#next-button");
      nextButton.text("Next");
      if (typeButton === "next") {
        console.log(page + 1);

        if (data.length < 10) {
          nextButton.attr("disabled", true);
        } else {
          nextButton.attr("page", page + 1);
          previousButton.attr("page", page);
        }
        if (page === 0) {
          previousButton.attr("disabled", true);
        } else {
          previousButton.attr("disabled", false);
        }
      } else {
        if (page === 0) {
          previousButton.attr("disabled", true);
        } else {
          previousButton.attr("page", page - 1);
          nextButton.attr("page", page);
        }
        nextButton.attr("disabled", false);
      }
    },
    error: function(error) {
      console.log(error);
    }
  });
}

function createRoleSelect(authority) {
  var select = $("<select>")
    .val(authority)
    .addClass("form-control authority")
    .append(
      $("<option>")
        .val("ROLE_USER")
        .text("USER")
    )
    .append(
      $("<option>")
        .val("ROLE_ADMIN")
        .text("ADMIN")
    )
    .append(
      $("<option>")
        .val("ROLE_STOCKKEEPER")
        .text("STOCKKEEPER")
    )
    .attr("disabled", true);
  select.find(`option[value=${authority}]`).attr("selected", "selected");
  return select;
}

function searchFieldsOnChange(event){
  $("#next-button").text("Search").attr("page", 0).attr("disabled", false);
  $("#previous-button").attr("page", 0).attr("disabled", true);
}
