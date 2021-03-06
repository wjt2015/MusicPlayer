package com.example.dao;

import java.util.List;

import com.example.entity.Song;

/** 
* @author ZTF  
* @date 2017年3月16日 下午9:13:16 
* @version 1.0 
* @Description:   Song的Dao接口
*/
public interface SongDao {
	void addSong(Song song,String menuName);
	//添加整个下目录歌曲
	void addSongWithFile(List<Song> songList,String menuName);
	//根据歌单名称获得歌曲列表
	List<Song> getSongList(String songSheet);
	List<Song> getSong(String key,String menuName,int Mode);
	List<Song> getSongHYBRID(String key,String menuName);
	void deleteSong(String songPath,String songMenu);
	void addMusicToLocal(Song song);
	boolean checkMusicExist(String path,String menuName);
	void setAllIsLike(String path,boolean isLike);
	boolean checkPathIsLike(String path);
}
 