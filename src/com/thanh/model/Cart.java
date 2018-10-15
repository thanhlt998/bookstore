package com.thanh.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<CartItem> cart;
	private int totalQuantity;
	private int totalPrice;

	public Cart() {
		this.cart = new ArrayList<>();
		totalQuantity = 0;
		totalPrice = 0;
	}

	public int isItemExisted(int bookId) {
		for (CartItem cartItem : cart) {
			if (cartItem.getBook().getBookId() == bookId) {
				return cart.indexOf(cartItem);
			}
		}
		return -1;
	}

	public void addCartItem(CartItem cartItem) {
		this.cart.add(cartItem);
		this.totalQuantity += cartItem.getQuantity();
		this.totalPrice += cartItem.getBook().getCurrentPrice() * cartItem.getQuantity();
	}

	public void increaseQuantityByBookId(int index, int bookId, int addQuantity) {
		this.cart.get(index).increaseQuantity(addQuantity);
		this.totalQuantity += addQuantity;
		this.totalPrice += this.cart.get(index).getBook().getCurrentPrice() * addQuantity;
	}
	
	public void removeCartItemByBookId(int bookId) {
		int index = isItemExisted(bookId);
		if(index != -1) {
			CartItem cartItem = cart.get(index);
			this.totalQuantity -= cartItem.getQuantity();
			this.totalPrice -= cartItem.getQuantity() * cartItem.getBook().getCurrentPrice();
			cart.remove(index);
		}
	}

	public List<CartItem> getCart() {
		return cart;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

}
