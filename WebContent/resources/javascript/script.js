$(document).ready(function () {
  // Validate form
  // ------------------------------------------------------------
  // var contextPath = $("#context-path").attr("context-path");

  var registerForm = $("#registerForm");

  validatePasswordRegisterForm(registerForm);

  registerForm.submit(validateSubmitRegisterForm);

  registerForm.find("#username")
    .blur(validateUsernameField);

  // Add cart ------------------------------------------------------------
  $("#add-button").click(increaseQuantity);
  $("#minus-button").click(decreaseQuantity);

  // Replace img ---------------------------------------------------------
  $(".small-img").click(replaceImg);

  $("#shopCart").hover(
    function () {
      $("#cartDetail")
        .stop()
        .slideDown();
    },
    function () {
      $("#cartDetail")
        .stop()
        .slideUp();
    }
  );

  $("#cartDetail")
    .on(
      "hover",
      ".remove-cart-item",
      function (event) {
        $(this)
          .stop()
          .animate({
              "font-size": "120%"
            },
            300,
            "swing"
          );
      },
      function (event) {
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

  // user management
  $("#view-all-users").on("click", ".fa-user-edit", editUserButtonClick);

  $("#view-all-users").on("click", ".fa-save", saveRoleChange);

  $("#view-all-users").on(
    "click",
    "#previous-button, #next-button",
    viewUsersButtonClick
  );

  $("#search-user-fields").on(
    "change",
    "#user-id, #username, #name, #authority",
    searchUserFieldsOnChange
  );

  //order management
  $("#view-all-orders").on(
    "click",
    "#previous-button, #next-button",
    viewOrderButtonClick
  );

  $("#search-order-fields").on(
    "change",
    "#user-id, #order-id, #order-status",
    searchOrderFieldsOnChange
  );

  $("#view-all-orders").on("click", ".fa-user-edit", editOrderButtonClick);

  $("#view-all-orders").on("click", ".fa-save", saveOrderChange);

  $("#order-id-input").blur(orderIdInputOnChange);

  $("#admin-view-order-button").click(adminViewOrderDetail);

  // category management
  $("#search-category-button").click(searchCategory);

  $("#add-category-button").click(addCategory);

  // manufaturer management
  $("#search-manufacturer-button").click(searchManufacturer);

  $("#add-manufacturer-button").click(addManufacturer);

  // book management
  $("#add-book-management").on("click", "#category-id", loadCategory);

  $("#add-book-management").on("click", "#manufacturer-id", loadManufacturer);

  // $("#add-book-management").on("click", "#add-book-button", addBook);

  $("#upload-images-form").submit(addBook);

  // promotion management
  $(".tab").on("click", "button", tabButtonClick);

  $("#add-promotion-button").click(addPromotionButtonClick);

  $("#view-promotion").on(
    "change",
    "#search-by-id, #search-by-description",
    searchPromotionFieldOnChange
  );

  $("#view-promotion").on(
    "click",
    "#previous-button, #next-button",
    viewPromotionButtonClick
  );

  $("#add-promotion-event").on("blur", "#promotion-id", promotionIdFieldOnBlur);

  $("#add-promotion-event").on(
    "change",
    "#book-id, #book-name",
    bookSearchFieldOnChange
  );

  $("#add-promotion-event").on(
    "click",
    "#previous-button, #next-button",
    viewBookButtonClick
  );

  $("#add-promotion-event").on(
    "click",
    ".add-promotion-event-button",
    addPromotionEventButtonClick
  );

  $("#view-promotion-event").on(
    "change",
    "#promotion-id",
    viewPromotionEventSearchFieldChange
  );

  $("#view-promotion-event").on(
    "click",
    "#previous-button, #next-button",
    viewPromotionEventButtonClick
  );

  // storage management
  $("#view-storage").on(
    "click",
    "#view-storage-button",
    viewStorageButtonClick
  );

  $("#view-storage").on("click", ".fa-edit", editStorageButtonClick);

  $("#view-storage").on("click", ".fa-save", saveStorageButtonClick);

  $("#add-storage").on("click", "#stock-keeper-id", stockKeeperIdFieldHover);

  $("#add-storage").on("click", "#add-storage-button", addStorageButtonClick);

  $("#import-book").on("click", "#storage-id", storageIdFieldClick);

  $("#import-book").on("click", "#import-book-button", importBookButtonClick);

  // view importation
  $("#view-importation").on(
    "change",
    "#importation-id, #storage-id, #book-id, #import-date",
    viewImportationSearchFieldChange
  );

  $("#view-importation").on(
    "click",
    "#next-button, #previous-button",
    viewImportationButtonClick
  );

  $("#view-importation").on("click", "#storage-id", storageIdFieldClick);

  // view-exportation
  $("#view-exportation").on(
    "change",
    "#exportation-id, #storage-id, #book-id, #export-date",
    viewExportationSearchFieldChange
  );

  $("#view-exportation").on(
    "click",
    "#next-button, #previous-button",
    viewExportationButtonClick
  );

  $("#view-exportation").on("click", "#storage-id", storageIdFieldClick);

  //revenue management
  $("#revenue-per-day").on(
    "click",
    "#view-revenue-per-day-button",
    viewRevenuePerDayButtonClick
  );

  $("#revenue-per-month").on(
    "click",
    "#view-revenue-per-month-button",
    viewRevenuePerMonthButtonClick
  );
});

// --------------------------------------------------------------------------------------------
// Validate Form

function validateSubmitRegisterForm(event) {
  var registerForm = $("#registerForm");
  var passwordField = registerForm.find("#password");
  var confirmPasswordField = registerForm.find("#confirmPassword");

  validatePasswordField(passwordField, event);
  validateConfirmPasswordField(passwordField, confirmPasswordField, event);
}

function validatePasswordRegisterForm(registerForm) {
  var passwordField = registerForm.find("#password");
  var confirmPasswordField = registerForm.find("#confirmPassword");

  passwordField.blur(function (event) {
    validatePasswordField(passwordField, event);
  });

  confirmPasswordField.blur(function (event) {
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
    success: function (data) {
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
    error: function (e) {
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
    success: function (data) {
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
    error: function (error) {
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
    success: function (data) {
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
    error: function (error) {
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
  var button = $(this);
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
      success: function (data) {
        console.log(data);
        if (data === false) {
          message =
            "Your order is not completed! Please add Cart and order again!";
        } else {
          message = "Your order is ordered successfully!";
          button.prop("disabled", true);
          alert(message);
          // $(this).prop("disabled", true);
          $(location).attr("href", contextPath);
        }
      },
      error: function (error) {
        message = "Some errors occured!";
        console.log(error);
      }
    });
  } else {
    message = "Please fill the information!";
  }
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
    success: function (data) {
      if (data === true) {
        tableProfile
          .find("#table-profile-feedback")
          .text("Your profile is updated!");
        $(this).attr("disabled", true);
      }
    },
    error: function (error) {
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
    success: function (data) {
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
    error: function (error) {
      console.log(error);
    }
  });
}

function dateFormat(milliseconds) {
  var date = new Date(milliseconds);
  return (
    date.getFullYear() +
    "-" +
    (date.getMonth() + 1 < 10 ?
      "0" + (date.getMonth() + 1) :
      date.getMonth() + 1) +
    "-" +
    (date.getDate() < 10 ? "0" + date.getDate() : date.getDate())
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
      success: function (data) {
        if (data === true) {
          $("#change-password-feedback").text("Your password is updated!");
        } else {
          $("#old-password-feedback").text("Old password isn't correct!");
          $("#new-password").empty();
          $("confirm-new-password").empty();
          $("#change-password-feedback").text("Fail to update your password!");
        }
      },
      error: function (error) {
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
  confirmNewPassword.blur({
      newPassword
    },
    validConfirmNewPassword
  );
  button.click({
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
    success: function (data) {
      if (data === false) {
        orderDetailView.css("display", "none");
        orderDetail
          .find("#order-detail-feedback")
          .text(`No Order with orderId = ${orderId} found.`);
      } else {
        orderDetail.find("#order-detail-feedback").empty();
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
    success: function (data) {
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
    error: function (error) {
      console.log(error);
      alert("Can't change the role of this user!");
    }
  });
}

function viewUsersButtonClick(event) {
  var usersManagement = $("#view-all-users");
  var typeButton = $(this).attr("type-button");
  var page = parseInt($(this).attr("page"));
  var contextPath = $(this).attr("context-path");
  var tableBody = usersManagement.find("tbody");
  var userId = usersManagement.find("#user-id").val();
  var username = usersManagement.find("#username").val();
  var name = usersManagement.find("#name").val();
  var authority = usersManagement.find("#authority").val();
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
    success: function (data) {
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
            )
          )
          .appendTo(tableBody);
      }

      var previousButton = usersManagement.find("#previous-button");
      var nextButton = usersManagement.find("#next-button");
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
    error: function (error) {
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

function searchUserFieldsOnChange(event) {
  $("#view-all-users")
    .find("#next-button")
    .text("Search")
    .attr("page", 0)
    .attr("disabled", false);
  $("#view-all-users")
    .find("#previous-button")
    .attr("page", 0)
    .attr("disabled", true);
}

// admin view order history

function viewOrderButtonClick(event) {
  var ordersManagement = $("#view-all-orders");
  var typeButton = $(this).attr("type-button");
  var page = parseInt($(this).attr("page"));
  var contextPath = $(this).attr("context-path");
  var tableBody = ordersManagement.find("tbody");
  var userId = ordersManagement.find("#user-id").val();
  var orderId = ordersManagement.find("#order-id").val();
  var orderStatus = ordersManagement.find("#order-status").val();
  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/searchOrders",
    data: {
      page: page,
      orderId: orderId,
      userId: userId,
      orderStatus: orderStatus
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      console.log(data);
      tableBody.empty();
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append(
            $("<td>")
            .addClass("align-middle")
            .text(data[i].orderId)
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .text(data[i].userId)
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .text(dateFormat(data[i].orderDate))
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .append(
              $("<input>")
              .addClass("form-control")
              .attr("type", "date")
              .val(dateFormat(data[i].shipDate))
              .attr("disabled", true)
              .attr("id", "ship-date")
            )
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .text(data[i].shipAddress)
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .text(data[i].totalAmount)
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .text(data[i].paymentMethod)
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .append(createOrderStatusSelect(data[i].orderStatus))
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .append(
              $("<i>")
              .addClass("fas fa-user-edit align-middle")
              .attr("context-path", contextPath)
            )
          )
          .appendTo(tableBody);
      }

      var previousButton = ordersManagement.find("#previous-button");
      var nextButton = ordersManagement.find("#next-button");
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
    error: function (error) {
      console.log(error);
    }
  });
}

function createOrderStatusSelect(orderStatus) {
  var select = $("<select>")
    .addClass("form-control")
    .attr("id", "order-status")
    .attr("disabled", true)
    .append(
      $("<option>")
      .val("PREPARING")
      .text("Preparing")
    )
    .append(
      $("<option>")
      .val("SHIPPING")
      .text("Shipping")
    )
    .append(
      $("<option>")
      .val("CANCELED")
      .text("Canceled")
    )
    .append(
      $("<option>")
      .val("FINISH")
      .text("Finish")
    );
  select.find(`option[value='${orderStatus}']`).attr("selected", "selected");
  return select;
}

function searchOrderFieldsOnChange(event) {
  $("#view-all-orders")
    .find("#next-button")
    .text("Search")
    .attr("disabled", false)
    .attr("page", 0);
  $("#view-all-orders")
    .find("#previous-button")
    .attr("disabled", true)
    .attr("page", 0);
}

function editOrderButtonClick(event) {
  var contextPath = $(this).attr("context-path");
  $(this)
    .parent()
    .parent()
    .find("#ship-date, #order-status")
    .attr("disabled", false);
  $(this).replaceWith(
    $("<i>")
    .addClass("far fa-save")
    .attr("context-path", contextPath)
  );
}

function saveOrderChange(event) {
  var editIcon = $(this);
  var trParent = $(this)
    .parent()
    .parent();
  var shipDate = trParent.find("#ship-date").val();
  var orderStatus = trParent.find("#order-status").val();
  var contextPath = $(this).attr("context-path");
  var orderId = trParent
    .find("td")
    .first()
    .text();

  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/changeOrder",
    data: {
      shipDate: shipDate,
      orderStatus: orderStatus,
      orderId: orderId
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      if (data === true) {
        editIcon.replaceWith(
          $("<i>")
          .addClass("fas fa-user-edit")
          .attr("context-path", contextPath)
        );
        trParent.find("#ship-date, #order-status").attr("disabled", true);
        alert(`Change order with orderId = ${orderId} successfully!`);
      } else {
        alert("Can't change this order!");
      }
    },
    error: function (error) {
      console.log(error);
      alert("Can't change this order!");
    }
  });
}

function adminViewOrderDetail(event) {
  var orderDetail = $("#order-detail");
  var orderId = orderDetail.find("#order-id-input").val();
  var contextPath = $(this).attr("context-path");
  var orderDetailView = orderDetail.find("#order-detail-view");
  var tableBody = orderDetail.find("tbody");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/adminViewOrderDetail",
    data: {
      orderId: orderId
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      if (data === false) {
        orderDetailView.css("display", "none");
        orderDetail
          .find("#order-detail-feedback")
          .text(`No Order with orderId = ${orderId} found.`);
      } else {
        orderDetail.find("#order-detail-feedback").empty();
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

function orderIdInputOnChange(event) {
  if ($(this).val() === "") {
    $(this)
      .parent()
      .find(".view-order-button")
      .attr("disabled", true);
  } else {
    $(this)
      .parent()
      .find(".view-order-button")
      .attr("disabled", false);
  }
}

// category-management

function searchCategory(event) {
  var viewCategory = $("#view-category");
  var categoryId = viewCategory.find("#category-id").val();
  var categoryName = viewCategory.find("#category-name").val();
  var tableBody = viewCategory.find("tbody");
  var contextPath = $(this).attr("context-path");
  tableBody.empty();
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/searchCategory",
    data: {
      categoryId: categoryId,
      categoryName: categoryName
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      if (data.length == 0) {
        viewCategory
          .find("#search-category-feedback")
          .text("No category found, try again!");
      }
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append($("<td>").text(data[i].categoryId))
          .append($("<td>").text(data[i].categoryName))
          .appendTo(tableBody);
      }
    },
    error: function (error) {
      console.log(error);
    }
  });
}

function addCategory(event) {
  var addCategoryDiv = $("#add-category");
  var categoryName = addCategoryDiv.find("#category-name").val();
  var contextPath = $(this).attr("context-path");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/addCategory",
    data: {
      categoryName: categoryName
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      if (data === true) {
        addCategoryDiv
          .find("#add-category-feedback")
          .text("Add category successfully!");
        addCategoryDiv.find("#category-name").val("");
      } else {
        addCategoryDiv
          .find("#add-category-feedback")
          .text("Fail to add category!");
      }
    },
    error: function (error) {
      console.log(error);
      addCategoryDiv
        .find("#add-category-feedback")
        .text("Fail to add category!");
    }
  });
}

function searchManufacturer(event) {
  var viewManufacturer = $("#view-manufacturer");
  var manufacturerId = viewManufacturer.find("#manufacturer-id").val();
  var manufacturerName = viewManufacturer.find("#manufacturer-name").val();
  var tableBody = viewManufacturer.find("tbody");
  var contextPath = $(this).attr("context-path");
  tableBody.empty();

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/searchManufacturer",
    data: {
      manufacturerId: manufacturerId,
      manufacturerName: manufacturerName
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      if (data.length == 0) {
        viewManufacturer
          .find("#search-manufacturer-feedback")
          .text("No manufacturer found, try again!");
      }
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append($("<td>").text(data[i].manufacturerId))
          .append($("<td>").text(data[i].manufacturerName))
          .appendTo(tableBody);
      }
    },
    error: function (error) {
      console.log(error);
    }
  });
}

function addManufacturer(event) {
  var addManufacturerDiv = $("#add-manufacturer");
  var manufacturerName = addManufacturerDiv.find("#manufacturer-name").val();
  var contextPath = $(this).attr("context-path");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/addManufacturer",
    data: {
      manufacturerName: manufacturerName
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      if (data === true) {
        addManufacturerDiv
          .find("#add-manufacturer-feedback")
          .text("Add manufacturer successfully!");
        addManufacturerDiv.find("#manufacturer-name").val("");
      } else {
        addManufacturerDiv
          .find("#add-manufacturer-feedback")
          .text("Fail to add manufacturer!");
      }
    },
    error: function (error) {
      console.log(error);
      addManufacturerDiv
        .find("#add-manufacturer-feedback")
        .text("Fail to add manufacturer!");
    }
  });
}

function loadCategory(event) {
  var selectTag = $(this);
  var contextPath = $(this).attr("context-path");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/loadCategory",
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      selectTag.empty();
      $("#category-feedback").text("");
      for (var i = 0; i < data.length; i++) {
        selectTag.append(
          $("<option>")
          .val(data[i].categoryId)
          .text(data[i].categoryName)
        );
      }
    },
    error: function (error) {
      console.log(error);
      $("#category-feedback").text("Can't load category");
    }
  });
}

function loadManufacturer(event) {
  var selectTag = $(this);
  var contextPath = $(this).attr("context-path");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/loadManufacturer",
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      selectTag.empty();
      $("#manufacturer-feedback").text("");
      for (var i = 0; i < data.length; i++) {
        selectTag.append(
          $("<option>")
          .val(data[i].manufacturerId)
          .text(data[i].manufacturerName)
        );
      }
    },
    error: function (error) {
      console.log(error);
      $("#manufacturer-feedback").text("Can't load manufacturer!");
    }
  });
}

function validateAddBook(
  categoryId,
  bookName,
  bookDescription,
  manufacturerId,
  author,
  price
) {
  var flag = true;
  if (categoryId === "") {
    flag = false;
    $("#category-id-feedback").text("Not leave this field blank!");
  } else {
    $("#category-id-feedback").text("");
  }

  if (!/.{8, 45}/.test(bookName)) {
    flag = false;
    $("#book-name-feedback").text(
      "Not leave this field blank and book name has the max size is 45 characters!"
    );
  } else {
    $("#book-name-feedback").text("");
  }

  if (bookDescription === "") {
    flag = false;
    $("#book-description-feedback").text("Not leave this field blank!");
  } else {
    $("#book-description-feedback").text("");
  }

  if (manufacturerId === "") {
    flag = false;
    $("#manufacturer-id-feedback").text("Not leave this field blank!");
  } else {}
}

function addBook(event) {
  event.preventDefault();
  var addBookManagement = $("#add-book-management");
  var contextPath = addBookManagement.attr("context-path");
  var categoryId = addBookManagement.find("#category-id").val();
  var bookName = addBookManagement.find("#book-name").val();
  var bookDescription = addBookManagement.find("#book-description").val();
  var manufacturerId = addBookManagement.find("#manufacturer-id").val();
  var author = addBookManagement.find("#author").val();
  var price = addBookManagement.find("#price").val();
  var size = $("#file").prop("files").length;
  if (size == 0) {
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/addBook",
    data: {
      categoryId: categoryId,
      bookName: bookName,
      bookDescription: bookDescription,
      manufacturerId: manufacturerId,
      author: author,
      price: price
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      uploadImages(data);
      $("#add-book-management-feedback").text("Add book successfully");
    },
    error: function (error) {
      $("#add-book-management-feedback").text("Cannot add this book!");
    }
  });
  } else {
    alert("You must upload with images");
  }
}

function uploadImages(bookId) {
  var formData = new FormData();
  var files = $("#file").prop("files");

  for (var i = 0; i < files.length; i++) {
    formData.append("file-" + i, files[i]);
  }

  formData.append("bookId", bookId);

  var contextPath = $("#upload-images-button").attr("context-path");
  var token = $("input[name='_csrf']").val();
  var header = "X-CSRF-TOKEN";
  var headers = {};
  headers[header] = token;
  $.ajax({
    type: "POST",
    url: contextPath + "/uploadImages",
    headers: headers,
    data: formData,
    cache: false,
    enctype: "multipart/form-data",
    contentType: false,
    processData: false,
    success: function (data) {
      console.log(data);
      $("#upload-images-feedback").text("Upload images successfully!");
    },
    error: function (error) {
      console.log(error);
      $("#upload-images-feedback").text("Fail to upload images!");
    }
  });
}

// promotion management

function tabButtonClick(event) {
  var active = $(this)
    .parent()
    .find(".active");
  $(active.attr("data-target")).css("display", "none");
  active.removeClass("active");
  $(this).addClass("active");
  $($(this).attr("data-target")).slideDown(400);
}

function validateAddPromotionForm(
  promotionDescription,
  fromDate,
  toDate,
  discount
) {
  var flag = true;

  var date = new Date();
  today =
    date.getFullYear().toString() +
    "-" +
    (date.getMonth() + 1 < 10 ?
      "0" + (date.getMonth() + 1) :
      date.getMonth() + 1) +
    "-" +
    (date.getDate() < 10 ? "0" + date.getDate() : date.getDate());

  if (promotionDescription === "") {
    $("#promotion-description-feedback").text(
      "Please not leave this field blank!!"
    );
    flag = false;
  } else {
    $("#promotion-description-feedback").text("");
  }

  if (fromDate === "") {
    $("#from-date-feedback").text("Please not leave this field blank!!");
    flag = false;
  } else if (fromDate < today) {
    $("#from-date-feedback").text("From Date should be not before today");
    flag = false;
  } else {
    $("#from-date-feedback").text("");
  }

  if (toDate === "") {
    $("#to-date-feedback").text("Please not leave this field blank!!");
    flag = false;
  } else if (fromDate >= toDate) {
    $("#to-date-feedback").text("'To Date' should be after 'From Date'");
    flag = false;
  } else {
    $("#to-date-feedback").text("");
  }

  if (discount === "") {
    $("#discount-feedback").text("Please not leave this field blank!!");
    flag = false;
  } else {
    $("#discount-feedback").text("");
  }
  return flag;
}

function addPromotionButtonClick(event) {
  var addPromotionForm = $("#add-promotion");
  var promotionDescription = addPromotionForm
    .find("#promotion-description")
    .val();
  var fromDate = addPromotionForm.find("#from-date").val();
  var toDate = addPromotionForm.find("#to-date").val();
  var discount = addPromotionForm.find("#discount").val();
  var contextPath = $(this).attr("context-path");

  if (
    validateAddPromotionForm(promotionDescription, fromDate, toDate, discount)
  ) {
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: contextPath + "/addPromotion",
      data: {
        promotionDescription: promotionDescription,
        fromDate: fromDate,
        toDate: toDate,
        discount: discount
      },
      dataType: "json",
      timeout: 10000,
      success: function (data) {
        $("#add-promotion-feedback").text("Add promotion successfully!");
      },
      error: function (error) {
        console.log(error);
        $("#add-promotion-feedback").text("Fail to add promotion!");
      }
    });
  }
}

function searchPromotionFieldOnChange(event) {
  $("#view-promotion")
    .find("#next-button")
    .text("Search")
    .attr("disabled", false)
    .attr("page", 0);
  $("#view-promotion")
    .find("#previous-button")
    .attr("disabled", true)
    .attr("page", 0);
}

function viewPromotionButtonClick(event) {
  var typeButton = $(this).attr("type-button");
  var page = parseInt($(this).attr("page"));
  var contextPath = $(this).attr("context-path");
  var viewPromotion = $("#view-promotion");
  var idSearchValue = viewPromotion.find("#search-by-id").val();

  var descriptionSearchValue = viewPromotion
    .find("#search-by-description")
    .val();

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/viewPromotion",
    data: {
      promotionId: idSearchValue,
      promotionDescription: descriptionSearchValue,
      page: page
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      var tableBody = viewPromotion.find("tbody");
      tableBody.empty();
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append(
            $("<td>")
            .addClass("align-middle")
            .text(data[i].promotionId)
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .text(data[i].promotionDescription)
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .append(dateFormat(data[i].fromDate))
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .append(dateFormat(data[i].toDate))
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .append(data[i].discount)
          )
          .appendTo(tableBody);
      }
      var previousButton = viewPromotion.find("#previous-button");
      var nextButton = viewPromotion.find("#next-button");
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
    error: function (error) {
      console.log(error);
    }
  });
}

function promotionIdFieldOnBlur(event) {
  var promotionId = $(this).val();
  var addPromotionEvent = $("#add-promotion-event");

  if (promotionId === "") {
    addPromotionEvent
      .find(".add-promotion-event-button")
      .attr("disabled", true);
  } else {
    var contextPath = $(this).attr("context-path");
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: contextPath + "/checkPromotionAvailable",
      data: {
        promotionId: promotionId
      },
      dataType: "json",
      timeout: 10000,
      success: function (data) {
        if (data === false) {
          addPromotionEvent
            .find(".add-promotion-event-button")
            .attr("disabled", true);
          addPromotionEvent
            .find("#promotion-id-check")
            .empty()
            .append($("<i>").addClass("far fa-times-circle text-danger"));
        } else {
          addPromotionEvent
            .find(".add-promotion-event-button")
            .attr("disabled", false);
          addPromotionEvent
            .find("#promotion-id-check")
            .empty()
            .append($("<i>").addClass("far fa-check-circle text-success"));
        }
      },
      error: function (error) {
        addPromotionEvent
          .find(".add-promotion-event-button")
          .attr("disabled", true);
        addPromotionEvent
          .find("#promotion-id-check")
          .empty()
          .append($("<i>").addClass("far fa-times-circle text-danger"));
        console.log(error);
      }
    });
  }
}

function bookSearchFieldOnChange(event) {
  $("#add-promotion-event")
    .find("#next-button")
    .text("Search")
    .attr("disabled", false)
    .attr("page", 0);
  $("#add-promotion-event")
    .find("#previous-button")
    .attr("disabled", true)
    .attr("page", 0);
}

function viewBookButtonClick(event) {
  var typeButton = $(this).attr("type-button");
  var page = parseInt($(this).attr("page"));
  var contextPath = $(this).attr("context-path");
  var parentTag = $(this).parent();
  var bookId = parentTag.find("#book-id").val();
  var bookName = parentTag.find("#book-name").val();
  var tableBody = parentTag.find("tbody");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/searchBookByIdName",
    data: {
      bookId: bookId,
      bookName: bookName,
      page: page
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      tableBody.empty();
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append($("<td>").text(data[i].bookId))
          .append($("<td>").text(data[i].bookName))
          .append($("<td>").text(data[i].author))
          .append(
            $("<td>").append(
              $("<button>")
              .addClass("btn btn-outline-dark add-promotion-event-button")
              .attr("context-path", contextPath)
              .text("Add")
            )
          )
          .appendTo(tableBody);

        if ($("#add-promotion-event").has(".fa-times-circle")) {
          tableBody.find(".add-promotion-event-button").attr("disabled", true);
        }
      }

      var previousButton = parentTag.find("#previous-button");
      var nextButton = parentTag.find("#next-button");
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
    error: function (error) {
      console.log(error);
    }
  });
}

function addPromotionEventButtonClick(event) {
  var contextPath = $(this).attr("context-path");
  var bookId = $(this)
    .parent()
    .siblings()
    .first()
    .text();
  var promotionId = $("#add-promotion-event")
    .find("#promotion-id")
    .val();

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/addPromotionEvent",
    data: {
      bookId: bookId,
      promotionId: promotionId
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      $("#add-promotion-event-feedback").text(
        "Add promotion event successfully!"
      );
    },
    error: function (error) {
      console.log(error);
      $("#add-promotion-event-feedback").text("Fail to add promotion event!");
    }
  });
}

function viewPromotionEventSearchFieldChange(event) {
  $("#view-promotion-event")
    .find("#next-button")
    .text("Search")
    .attr("disabled", false)
    .attr("page", 0);
  $("#view-promotion-event")
    .find("#previous-button")
    .attr("disabled", true)
    .attr("page", 0);
}

function viewPromotionEventButtonClick(event) {
  var typeButton = $(this).attr("type-button");
  var page = parseInt($(this).attr("page"));
  var contextPath = $(this).attr("context-path");

  var parentTag = $(this).parent();
  var promotionId = parentTag.find("#promotion-id").val();
  var tableBody = parentTag.find("tbody");

  if (promotionId !== "") {
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: contextPath + "/viewPromotionEvent",
      data: {
        promotionId: promotionId,
        page: page
      },
      dataType: "json",
      timeout: 10000,
      success: function (data) {
        tableBody.empty();
        for (var i = 0; i < data.length; i++) {
          $("<tr>")
            .append($("<td>").text(data[i].bookId))
            .append($("<td>").text(data[i].bookName))
            .append($("<td>").text(data[i].author))
            .appendTo(tableBody);
        }

        var previousButton = parentTag.find("#previous-button");
        var nextButton = parentTag.find("#next-button");
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
      error: function (error) {
        console.log(error);
      }
    });
  }
}

// storage management

function createSelectStockKeeper(stockKeeperIdList, stockKeeperId) {
  var select = $("<select>")
    .addClass("form-control")
    .attr("id", "stock-keeper-id")
    .attr("disabled", true);
  for (var i = 0; i < stockKeeperIdList.length; i++) {
    $("<option>")
      .val(stockKeeperIdList[i])
      .text(stockKeeperIdList[i])
      .appendTo(select);
  }
  select.find(`option[value=${stockKeeperId}]`).attr("selected", "selected");
  return select;
}

function viewStorage(stockKeeperIdList, tableBody, contextPath) {
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/viewStorage",
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      tableBody.empty();
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append(
            $("<td>")
            .text(data[i].storageId)
            .attr("id", "storage-id")
          )
          .append(
            $("<td>").append(
              $("<input>")
              .addClass("form-control")
              .attr("id", "storage-name")
              .val(data[i].storageName)
              .attr("disabled", true)
            )
          )
          .append(
            $("<td>").append(
              $("<input>")
              .addClass("form-control")
              .attr("id", "storage-address")
              .val(data[i].storageAddress)
              .attr("disabled", true)
            )
          )
          .append(
            $("<td>").append(
              createSelectStockKeeper(stockKeeperIdList, data[i].stockKeeperId)
            )
          )
          .append(
            $("<td>")
            .addClass("align-middle")
            .append(
              $("<i>")
              .addClass("far fa-edit")
              .attr("context-path", contextPath)
            )
          )
          .appendTo(tableBody);
      }
    },
    error: function (error) {
      console.log(error);
    }
  });
}

function editStorageButtonClick(event) {
  var contextPath = $(this).attr("context-path");
  $(this)
    .parent()
    .parent()
    .find("input, select")
    .attr("disabled", false);
  $(this).replaceWith(
    $("<i>")
    .addClass("far fa-save")
    .attr("context-path", contextPath)
  );
}

function viewStorageButtonClick(contextPath, event) {
  var contextPath = $(this).attr("context-path");
  var tableBody = $(this)
    .parent()
    .find("tbody");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/getStockKeeperIdList",
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      viewStorage(data, tableBody, contextPath);
    },
    error: function (error) {
      console.log(error);
    }
  });
}

function saveStorageButtonClick(event) {
  var tdParent = $(this)
    .parent()
    .parent();
  var storageId = tdParent.find("#storage-id").text();
  var storageName = tdParent.find("#storage-name").val();
  var storageAddress = tdParent.find("#storage-address").val();
  var stockKeeperId = tdParent.find("#stock-keeper-id").val();
  var contextPath = $(this).attr("context-path");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/saveStorage",
    data: {
      storageId: storageId,
      storageName: storageName,
      storageAddress: storageAddress,
      stockKeeperId: stockKeeperId
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      $("#view-storage-feedback").text("Save changes successfully!");
      tdParent.find(".fa-save").replaceWith(
        $("<i>")
        .addClass("far fa-edit")
        .attr("context-path", contextPath)
      );
      tdParent.find("input, select").attr("disabled", true);
    },
    error: function (error) {
      console.log(error);
      $("#view-storage-feedback").text("Fail to save changes!");
    }
  });
}

function stockKeeperIdFieldHover(event) {
  var selectTag = $(this);
  var contextPath = selectTag.attr("context-path");

  if (selectTag.find("option").length === 0) {
    selectTag.empty();
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: contextPath + "/getStockKeeperIdList",
      dataType: "json",
      timeout: 10000,
      success: function (data) {
        for (var i = 0; i < data.length; i++) {
          $("<option>")
            .val(data[i])
            .text(data[i])
            .appendTo(selectTag);
        }
      },
      error: function (error) {
        selectTag
          .siblings("#stock-keeper-id-feedback")
          .text("Can'load Stock Keeper Id List.");
        console.log(error);
      }
    });
  }
}

function validateStorageInfo(storageName, storageAddress, stockKeeperId) {
  var flag = true;

  if (storageName === "" || storageName.length >= 45) {
    $("#storage-name-feedback").text(
      "This field must not be blank and contains less than 45 characters!"
    );
    flag = false;
  } else {
    $("#storage-name-feedback").text("");
  }

  if (storageAddress === "" || storageAddress.length >= 45) {
    $("#storage-address-feedback").text(
      "This field must not be blank and contains less than 45 characters!"
    );
    flag = false;
  } else {
    $("#storage-address-feedback").text("");
  }

  if (stockKeeperId === "") {
    $("#stock-keeper-id-feedback").text("This field must not be blank!");
    flag = false;
  } else {
    $("#stock-keeper-id-feedback").text("");
  }

  return flag;
}

function addStorageButtonClick(event) {
  var contextPath = $(this).attr("context-path");
  var addStorage = $("#add-storage");
  var storageName = addStorage.find("#storage-name").val();
  var storageAddress = addStorage.find("#storage-address").val();
  var stockKeeperId = addStorage.find("#stock-keeper-id").val();

  if (validateStorageInfo(storageName, storageAddress, stockKeeperId)) {
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: contextPath + "/addStorage",
      data: {
        storageName: storageName,
        storageAddress: storageAddress,
        stockKeeperId: stockKeeperId
      },
      success: function (data) {
        $("#add-storage-feedback").text("Add storage successfully!");
      },
      error: function (error) {
        console.log(error);
        $("#add-storage-feedback").text("Fail to add storage!");
      }
    });
  }
}

function storageIdFieldClick() {
  var selectTag = $(this);
  var contextPath = selectTag.attr("context-path");

  if (selectTag.find("option").length <= 1) {
    selectTag.empty();
    // $("<option>")
    //   .text("Storage Id")
    //   .appendTo(selectTag);
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: contextPath + "/getStorageIdList",
      dataType: "json",
      timeout: 10000,
      success: function (data) {
        for (var i = 0; i < data.length; i++) {
          $("<option>")
            .val(data[i])
            .text(data[i])
            .appendTo(selectTag);
        }
      },
      error: function (error) {
        selectTag
          .siblings("#storage-id-feedback")
          .text("Can'load Storage Id List.");
        console.log(error);
      }
    });
  }
}

function validateImportBookInfo(
  storageId,
  bookId,
  quantity,
  importPrice,
  contextPath
) {
  var flag = true;
  var importBook = $("#import-book");
  if (storageId === "") {
    importBook
      .find("#storage-id-feedback")
      .text("This field must not be blank!");
    flag = false;
  } else {
    importBook.find("#storage-id-feedback").text("");
  }

  if (bookId === "") {
    importBook.find("#book-id-feedback").text("This field must not be blank!");
    flag = false;
  } else {
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: contextPath + "/checkBookIdExist",
      data: {
        bookId: bookId
      },
      dataType: "json",
      timeout: 10000,
      success: function (data) {
        if (data === false) {
          importBook
            .find("#book-id-feed-back")
            .text("This book id is not existed!");
          flag = false;
        } else {
          importBook.find("#book-id-feed-back").text("");
        }
      },
      error: function (error) {
        console.log(error);
        flag = false;
        importBook.find("#book-id-feed-back").text("Some thing went wrong!");
      }
    });
  }

  if (quantity === "") {
    importBook.find("#quantity-feedback").text("This field must not be blank!");
    flag = false;
  } else {
    importBook.find("#quantity-feedback").text("");
  }

  if (importPrice === "") {
    importBook
      .find("#import-price-feedback")
      .text("This field must not be blank!");
    flag = false;
  } else {
    importBook.find("#import-price-feedback").text("");
  }

  return flag;
}

function importBookButtonClick(event) {
  var importBook = $("#import-book");
  var contextPath = $(this).attr("context-path");
  var storageId = importBook.find("#storage-id").val();
  var bookId = importBook.find("#book-id").val();
  var quantity = importBook.find("#quantity").val();
  var importPrice = importBook.find("#import-price").val();

  if (
    validateImportBookInfo(
      storageId,
      bookId,
      quantity,
      importPrice,
      contextPath
    )
  ) {
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: contextPath + "/importBook",
      data: {
        storageId: storageId,
        bookId: bookId,
        quantity: quantity,
        importPrice: importPrice
      },
      dataType: "json",
      timeout: 10000,
      success: function (data) {
        importBook
          .find("#import-book-feedback")
          .text("Import book successfully!");
      },
      error: function (error) {
        console.log(error);
        importBook.find("#import-book-feedback").text("Fail to import book!");
      }
    });
  }
}

// view importation

function viewImportationSearchFieldChange(event) {
  $("#view-importation")
    .find("#next-button")
    .text("Search")
    .attr("disabled", false)
    .attr("page", 0);
  $("#view-importation")
    .find("#previous-button")
    .attr("disabled", true)
    .attr("page", 0);
}

function viewImportationButtonClick(event) {
  var typeButton = $(this).attr("type-button");
  var page = parseInt($(this).attr("page"));
  var contextPath = $(this).attr("context-path");

  var parentTag = $(this).parent();
  var importationId = parentTag.find("#importation-id").val();
  var storageId = parentTag.find("#storage-id").val();
  var bookId = parentTag.find("#book-id").val();
  var importDate = parentTag.find("#import-date").val();
  var tableBody = parentTag.find("tbody");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/viewImportation",
    data: {
      importationId: importationId,
      storageId: storageId,
      bookId: bookId,
      importDate: importDate === "" ? importDate : dateFormat(importDate),
      page: page
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      tableBody.empty();
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append($("<td>").text(data[i].importationId))
          .append($("<td>").text(data[i].storageId))
          .append($("<td>").text(data[i].bookId))
          .append($("<td>").text(data[i].quantity))
          .append($("<td>").text(dateFormat(data[i].importDate)))
          .append($("<td>").text(data[i].importPrice))
          .appendTo(tableBody);
      }

      var previousButton = parentTag.find("#previous-button");
      var nextButton = parentTag.find("#next-button");
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
    error: function (error) {
      console.log(error);
    }
  });
}

// view exportation
function viewExportationSearchFieldChange(event) {
  $("#view-exportation")
    .find("#next-button")
    .text("Search")
    .attr("disabled", false)
    .attr("page", 0);
  $("#view-exportation")
    .find("#previous-button")
    .attr("disabled", true)
    .attr("page", 0);
}

function viewExportationButtonClick(event) {
  var typeButton = $(this).attr("type-button");
  var page = parseInt($(this).attr("page"));
  var contextPath = $(this).attr("context-path");

  var parentTag = $(this).parent();
  var exportationId = parentTag.find("#exportation-id").val();
  var storageId = parentTag.find("#storage-id").val();
  var bookId = parentTag.find("#book-id").val();
  var exportDate = parentTag.find("#export-date").val();
  var tableBody = parentTag.find("tbody");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/viewExportation",
    data: {
      exportationId: exportationId,
      storageId: storageId,
      bookId: bookId,
      exportDate: exportDate === "" ? exportDate : dateFormat(exportDate),
      page: page
    },
    dataType: "json",
    timeout: 10000,
    success: function (data) {
      tableBody.empty();
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append($("<td>").text(data[i].exportationId))
          .append($("<td>").text(data[i].storageId))
          .append($("<td>").text(data[i].bookId))
          .append($("<td>").text(data[i].quantity))
          .append($("<td>").text(dateFormat(data[i].exportDate)))
          .appendTo(tableBody);
      }

      var previousButton = parentTag.find("#previous-button");
      var nextButton = parentTag.find("#next-button");
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
    error: function (error) {
      console.log(error);
    }
  });
}

// revenue management

function viewRevenuePerDayButtonClick(event) {
  var revenuePerDay = $("#revenue-per-day");
  var month = $(this)
    .siblings("#month")
    .val();
  var contextPath = $(this).attr("context-path");

  if (month !== "") {
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: contextPath + "/getRevenuePerDay",
      data: {
        month: month
      },
      dataType: "json",
      timeout: 10000,
      success: function (data) {
        fillRevenueChart(data, "Revenue Per Day", revenuePerDay);
      },
      error: function (error) {
        console.log(error);
      }
    });
  }
}

function fillRevenueChart(revenueList, title, revenueCanvas) {
  var myChart = revenueCanvas.find("#myChart")[0].getContext("2d");
  var config = {
    type: "line",
    data: {
      labels: Array.from(revenueList, (value, index) => index + 1),
      datasets: [{
        label: title,
        backgroundColor: "rgb(255, 99, 132)",
        borderColor: "rgb(255, 99, 132)",
        fill: false,
        data: revenueList
      }]
    },
    options: {
      responsive: true,
      title: {
        display: true,
        text: title
      },
      scales: {
        xAxes: [{
          display: true
        }],
        yAxes: [{
          display: true,
          type: "logarithmic"
        }]
      }
    }
  };
  var chart = new Chart(myChart, config);
}

function viewRevenuePerMonthButtonClick(event) {
  var revenuePerMonth = $("#revenue-per-month");
  var year = $(this)
    .siblings("#year")
    .val();
  var contextPath = $(this).attr("context-path");

  if (year !== "") {
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: contextPath + "/getRevenuePerMonth",
      data: {
        year: year
      },
      dataType: "json",
      timeout: 10000,
      success: function (data) {
        fillRevenueChart(data, "Revenue Per Month", revenuePerMonth);
      },
      error: function (error) {
        console.log(error);
      }
    });
  }
}