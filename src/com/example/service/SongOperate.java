package com.example.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.dao.SongDao;
import com.example.dao.impl.SongDaoImpl;
import com.example.entity.Song;
import com.example.entity.SongMenu;
import com.example.entity.Tag;
import com.example.util.SongUtil;
import com.example.util.TagInfoUtil;
import com.sun.org.apache.bcel.internal.classfile.Field;

import javafx.scene.media.Media;


/** 
* @date 2017年3月18日 上午1:19:59 
* @version 1.0 
* @Description:   
*/
public class SongOperate {
	static SongDao songDao;
	static{
		songDao=new SongDaoImpl();
	}
	
	public static List<Song> findSongByName(String songName,String menuName){
		return songDao.getSongByName(songName, menuName);
	}
	
	
	//添加一个song，并返回实体
	public static Song addSong(String path,String menuName){
		//此处判断是否为合法路径以及合法音频格式
		
		Song song=new Song();
		song.setPath(path);
		File file=new File(path);
		song.setLength(SongUtil.getLengthToMb(file.length()));
		Tag tag;
		if(path.endsWith(".flac")){
			tag= TagInfoUtil.FlacInfoRead(path);
			song.setTag(tag);
		}
		else{
			tag = TagInfoUtil.Mp3InfoRead(path);
			song.setTag(tag);
		}
		songDao.addSong(song,menuName);
		return song;
	}
	
	public static void addSongWithFile(String filePath,String menuName){
		File files=new File(filePath);
		List<Song> songList=new ArrayList<Song>();
		if(files.exists()&&files.isDirectory()){
			for(File file:files.listFiles()){
//				Media media=new Media(file.toString());
				String absolutePath = file.getAbsolutePath();
				if(file.isFile()){
					if(absolutePath.endsWith(".flac")){
						Tag tag = null ;
						tag=TagInfoUtil.FlacInfoRead(absolutePath);
						Song song=new Song();
						song.setPath(absolutePath);
						song.setLength(SongUtil.getLengthToMb(file.length()));
						song.setTag(tag);
						songList.add(song);
					}
					else if(absolutePath.endsWith(".mp3")){
						Tag tag = null ;
						tag= TagInfoUtil.Mp3InfoRead(absolutePath);
						Song song=new Song();
						song.setPath(absolutePath);
						song.setLength(SongUtil.getLengthToMb(file.length()));
						song.setTag(tag);
						songList.add(song);
					}

				}
			}
		}
		songDao.addSongWithFile(songList, menuName);
	}
	
	public static void main(String[] args) {
		addSong("D:\\Angel With a Shotgun.mp3", "我的最爱");
		
//		List<SongMenu> allSongMenu = SongMenuOperate.getAllSongMenu();
//		for(SongMenu songMenu:allSongMenu){
//			System.out.println(songMenu.getSongMenuName());
//			List<Song> songList = songMenu.getSongList();
//			for(Song song:songList){
//				System.out.println("--"+song.getPath());
//				System.out.println("--"+song.getTag().getAlbum());
//				System.out.println("--"+song.getTag().getArtist());
//				System.out.println("--"+song.getTag().getSongName());
//			}
//		}
		
//		
//		List<Song> songsByMenuName = SongMenuOperate.getSongsByMenuName("我的最爱");
//		for(Song song:songsByMenuName){
//			System.out.println("--"+song.getPath());
//			System.out.println("--"+song.getTag().getAlbum());
//			System.out.println("--"+song.getTag().getArtist());
//			System.out.println("--"+song.getTag().getSongName());
//		}
		
		
//		addSongWithFile("D:\\CloudMusic","我的最爱");
//		List<Song> findSongByName = findSongByName("心做","我的最爱");
//		Iterator<Song> iterator = findSongByName.iterator();
//		while(iterator.hasNext()){
//			Song next = iterator.next();
//			System.out.println(next.getTag().getSongName());
//		}
	}
	
}
 