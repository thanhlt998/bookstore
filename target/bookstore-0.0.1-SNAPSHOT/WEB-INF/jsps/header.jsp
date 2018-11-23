<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!-- Showcase -->
<section id="showcase">
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li class="active" data-target="#myCarousel" data-slide-to="0"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item carousel-image-1 active">
				<div class="container">
					<div class="carousel-caption d-none d-lg-block text-right mb-5">
						<blockquote class="blockquote text-right">
							<p class="mb-0 display-4">A person who won't read has no advantage over one who can't read.</p>
							<p class="display-5">Một người không đọc sách chẳng hơn gì kẻ không biết đọc.</p>
							<footer class="blockquote-footer"><cite title="Source Title">Mark Twain</cite></footer>
						</blockquote>
					</div>
				</div>
			</div>

			<div class="carousel-item carousel-image-2">
				<div class="container">
					<div class="carousel-caption d-none d-lg-block mb-5">
						<blockquote class="blockquote text-right">
							<p class="mb-0 display-4">Any man who reads too much and uses his own brain too little
								falls into lazy habits of thinking.</p>
							<p class="display-5">Người đọc quá nhiều và dùng tới bộ óc quá ít sẽ rơi vào thói quen suy nghĩ lười biếng.</p>
							<footer class="blockquote-footer"><cite title="Source Title">Albert Einstein</cite></footer>
						</blockquote>
					</div>
				</div>
			</div>

			<div class="carousel-item carousel-image-3">
				<div class="container">
					<div class="carousel-caption d-none d-lg-block text-right mb-5">
						<blockquote class="blockquote text-right text-light">
							<p class="mb-0 display-4">Good books, like good friends, are few and chosen; the more
								select, the more enjoyable.</p>
							<p class="display-5">Sách hay, cũng như bạn tốt, ít và được chọn lựa; chọn lựa càng nhiều, thưởng thức càng nhiều.</p>
							<footer class="blockquote-footer"><cite title="Source Title">Louisa May Alcott</cite></footer>
						</blockquote>
					</div>
				</div>
			</div>
		</div>
		<a href="#myCarousel" data-slide="prev" class="carousel-control-prev">
			<span class="carousel-control-prev-icon"></span>
		</a>
		<a href="#myCarousel" data-slide="next" class="carousel-control-next">
			<span class="carousel-control-next-icon"></span>
		</a>
	</div>
</section>

<!-- Introduction -->
<section id="introduction">
	<div class="container mb-3 py-3">
		<div class="row">
			<div class="col-md-3">
				<div class="card text-dark text-center p-3">
					<h2 class="card-title">Quick Delivery</h2>
					<i class="fas fa-truck fa-3x"></i>
					<hr>
					<p>Vận chuyển nhanh chóng, an toàn.</p>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card text-dark text-center p-3">
					<h2 class="card-title">Pay With Easy</h2>
					<i class="far fa-credit-card fa-3x"></i>
					<hr>
					<p>Thanh toán tiện lợi, nhanh chóng.</p>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card text-dark text-center p-3">
					<h2 class="card-title">Best Deal</h2>
					<i class="fas fa-tags fa-3x"></i>
					<hr>
					<p>Cam kết giá tốt nhất trên thị trường.</p>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card text-dark text-center p-3">
					<h2 class="card-title">Security</h2>
					<i class="fas fa-user-shield fa-3x"></i>
					<hr>
					<p>Cam kết thông tin khách hàng được bảo mật.</p>
				</div>
			</div>
		</div>
	</div>
</section>