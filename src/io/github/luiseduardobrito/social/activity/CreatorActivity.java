package io.github.luiseduardobrito.social.activity;

import io.github.luiseduardobrito.social.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

@EActivity(R.layout.activity_creator)
@OptionsMenu(R.menu.creator)
public class CreatorActivity extends Activity {

	static final String TAG = "CREATOR";
	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final int REQUEST_VIDEO_CAPTURE = 2;
	static final int REQUEST_IMAGE_PICKER = 3;

	File file;
	String mCurrentPhotoPath;

	@ViewById(R.id.imageView)
	ImageView mImageView;

	@ViewById(R.id.videoView)
	VideoView mVideoView;

	@OptionsItem
	void actionCapture() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		}
	}

	@OptionsItem
	void actionVideo() {
		Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
		}
	}

	@OptionsItem
	void actionGallery() {
		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, REQUEST_IMAGE_PICKER);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && null != intent) {

			Bundle extras = intent.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");

			saveBitmap(imageBitmap);
			galleryAddPic();

			mVideoView.setVisibility(View.GONE);
			mImageView.setVisibility(View.VISIBLE);
			
			mImageView.setImageBitmap(imageBitmap);
		}

		else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK && null != intent) {

			Uri videoUri = intent.getData();

			mImageView.setVisibility(View.GONE);
			mVideoView.setVisibility(View.VISIBLE);

			mVideoView.setVideoURI(videoUri);
		}

		else if (requestCode == REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK) {
			
			Bitmap bitmap = getBitmapFromCameraData(intent, this);
			
			mVideoView.setVisibility(View.GONE);
			mImageView.setVisibility(View.VISIBLE);
			
			mImageView.setImageBitmap(bitmap);
		}

	}

	private void galleryAddPic() {
		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(mCurrentPhotoPath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		this.sendBroadcast(mediaScanIntent);
	}

	public static Bitmap getBitmapFromCameraData(Intent data, Context context) {
		Uri selectedImage = data.getData();
		String[] filePathColumn = { MediaStore.Images.Media.DATA };
		Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null,
				null, null);
		cursor.moveToFirst();
		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		String picturePath = cursor.getString(columnIndex);
		cursor.close();
		return BitmapFactory.decodeFile(picturePath);
	}

	public void saveBitmap(Bitmap capturedBitmap) {

		String appName = getString(R.string.app_name);
		appName = Pattern.compile("\\s+").matcher(appName).replaceAll(" ").trim();

		String path = Environment.getExternalStorageDirectory().toString();
		OutputStream fOutputStream = null;

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
				.format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";

		File file = new File(path + "/" + appName + "/", imageFileName + ".jpg");
		File parent = new File(file.getParent());

		mCurrentPhotoPath = file.getAbsolutePath();

		try {

			if (!parent.exists()) {
				parent.mkdirs();
			}

			if (!file.exists()) {
				file.createNewFile();
			}

			fOutputStream = new FileOutputStream(file);
			capturedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOutputStream);

			fOutputStream.flush();
			fOutputStream.close();

			MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(),
					file.getName(), file.getName());

		} catch (FileNotFoundException e) {

			e.printStackTrace();
			Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
			return;

		} catch (IOException e) {

			e.printStackTrace();
			Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
			return;
		}
	}
}
