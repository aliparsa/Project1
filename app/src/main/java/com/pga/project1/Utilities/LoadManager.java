//package com.pga.project1.Utilities;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.Map;
//import java.util.Queue;
//import java.util.Stack;
//import java.util.concurrent.ConcurrentHashMap;
//
//import android.graphics.Bitmap;
//import android.os.AsyncTask;
//import android.support.v4.util.LruCache;
//import android.view.View;
//import android.widget.ImageView;
//
//public class LoadManager {
//
//	public static Stack<UrlAndImageviewPlaceholder> urlStack = new Stack<UrlAndImageviewPlaceholder>();
//	public static Map<String, Bitmap> map = new HashMap<String, Bitmap>();
//
//	public static void load(String url, ImageView imageview, Bitmap loading) {
//
//		if (url == null)
//			return;
//
//		if (map.containsKey(url)) {
//			// TODO load from cache
//			imageview.setImageBitmap(map.get(url));
//
//		} else {
//			// TODO load from Internet
//			if (urlStack.isEmpty()) {
//
//				urlStack.push(new UrlAndImageviewPlaceholder(url, imageview));
//				loadNext();
//			} else {
//				urlStack.push(new UrlAndImageviewPlaceholder(url, imageview));
//			}
//
//		}
//	}
//
//	public static void loadFinished(String url, Bitmap bmp) {
//		map.put(url, bmp);
//		loadNext();
//	}
//
//	public static void loadNext() {
//		if (!urlStack.isEmpty()) {
//			UrlAndImageviewPlaceholder tmp = urlStack.pop();
//
//			if (map.containsKey(tmp.url)) {
//				// TODO load from cache
//				tmp.imageview.setImageBitmap(map.get(tmp.url));
//
//			} else {
//
//				new AsynLoadImage(tmp.imageview, tmp.url).execute("");
//			}
//		}
//	}
//
//}
