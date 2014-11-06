package com.example.kjwprogexercise;

import java.util.ArrayList;
import java.util.List;

import com.example.kjwprogexercise.model.Offer;
import com.example.kjwprogexercise.util.OfferDataAdapter;
import com.example.kjwprogexercise.util.WebServiceClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

public class MainActivity extends ListActivity {

	private OfferDataAdapter adapter;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new OfferDataAdapter(new ArrayList<Offer>());
		setListAdapter(adapter);

		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("Fetching data");
		progressDialog.setTitle("Loading");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
		ImageLoader.getInstance().init(config);
	}

	@Override
	protected void onResume() {
		super.onResume();
		fetchOffersTask.execute(null, null, null);
	}

	@Override
	protected void onPause() {
		progressDialog.cancel();
		super.onPause();
	}

	@Override
	protected void onStop() {
		progressDialog.cancel();
		super.onStop();
	}

	AsyncTask<Void, Integer, List<Offer>> fetchOffersTask = new AsyncTask<Void, Integer, List<Offer>>() {

		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}

		protected void onPostExecute(List<Offer> results) {
			if (results != null) {
				adapter.setOffers(results);
				adapter.notifyDataSetChanged();
			}
			progressDialog.cancel();
		}

		@Override
		protected List<Offer> doInBackground(Void... params) {
			WebServiceClient webService = WebServiceClient.getInstance();
			return webService.fetchOffers();
		}
	};
}
