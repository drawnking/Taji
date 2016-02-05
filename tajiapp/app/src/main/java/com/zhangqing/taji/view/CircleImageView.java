package com.zhangqing.taji.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

public class CircleImageView extends ImageView {
	private boolean allowedRedraw = true;
	private Context context;

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		allowedRedraw = true;
		this.context=context;
		// TODO Auto-generated constructor stub
	}

	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		allowedRedraw = true;
		this.context=context;
		// TODO Auto-generated constructor stub
	}

	public CircleImageView(Context context) {
		super(context);
		allowedRedraw = true;
		this.context=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//super.onDraw(canvas);
		// setLayoutParams(new LayoutParams(getHeight(), getHeight()));
		Toast.makeText(context,getParent().toString()+"", Toast.LENGTH_SHORT).show();
		if (allowedRedraw == false)
			return;
		
		setScaleType(ScaleType.FIT_XY);
		Drawable drawable = getDrawable();

		if (null == drawable) {
			return;
		}

		// 将drawable转换成bitmap==>网上找的
		// Bitmap bitmap = Bitmap
		// .createBitmap(
		// drawable.getIntrinsicWidth(),
		// drawable.getIntrinsicHeight(),
		// drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
		// : Bitmap.Config.RGB_565);
		//

		Bitmap bitmap = Bitmap.createBitmap(getHeight(), getHeight(), drawable
				.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565);

		Canvas srcCanvas = new Canvas(bitmap);

		// drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
		// drawable.getIntrinsicHeight());
		drawable.setBounds(0, 0, getHeight(), getHeight());

		drawable.draw(srcCanvas);

		float cx = getHeight() / 2;
		float cy = getHeight() / 2;

		float radius = getHeight() / 2;
//
//		Paint borderPaint = new Paint();
//		borderPaint.setAntiAlias(true);
//		borderPaint.setColor(Color.GREEN);
//
//		canvas.drawCircle(cx, cy, radius, borderPaint);

		// 画图
		BitmapShader shader = new BitmapShader(bitmap, TileMode.CLAMP,
				TileMode.CLAMP);
		Paint paint = new Paint();
		paint.setShader(shader);
		paint.setAntiAlias(true);

		canvas.drawCircle(cx, cy, radius - 0, paint);
		allowedRedraw = false;
		
		
	}

}
