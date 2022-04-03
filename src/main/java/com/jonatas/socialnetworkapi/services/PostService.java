package com.jonatas.socialnetworkapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.entities.Comment;
import com.jonatas.socialnetworkapi.entities.Entity;
import com.jonatas.socialnetworkapi.entities.Episode;
import com.jonatas.socialnetworkapi.entities.Group;
import com.jonatas.socialnetworkapi.entities.Post;
import com.jonatas.socialnetworkapi.entities.Season;
import com.jonatas.socialnetworkapi.entities.User;
import com.jonatas.socialnetworkapi.entities.dto.PostQuestDTO;
import com.jonatas.socialnetworkapi.entities.dto.PostTalkDTO;
import com.jonatas.socialnetworkapi.entities.dto.PostTalkGroupDTO;
import com.jonatas.socialnetworkapi.entities.dto.PostUpdateDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.CommentMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.PostQuestMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.PostTalkGroupMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.PostTalkMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.PostUpdateMiniDTO;
import com.jonatas.socialnetworkapi.entities.dto.mini.UserMiniDTO;
import com.jonatas.socialnetworkapi.entities.helper.LikeUser;
import com.jonatas.socialnetworkapi.entities.helper.PostUser;
import com.jonatas.socialnetworkapi.entities.helper.VoteQuest;
import com.jonatas.socialnetworkapi.entities.post.Quest;
import com.jonatas.socialnetworkapi.entities.post.TalkGroup;
import com.jonatas.socialnetworkapi.entities.post.Update;
import com.jonatas.socialnetworkapi.enuns.TypeObject;
import com.jonatas.socialnetworkapi.enuns.TypePost;
import com.jonatas.socialnetworkapi.enuns.TypePostVisibility;
import com.jonatas.socialnetworkapi.repositories.PostRepository;

@Service
public class PostService {
	
	//repositories

	@Autowired
	private PostRepository postRepository;
	
	//services
	
	@Autowired
	@Lazy
	private UserService userService;
	
	@Autowired
	@Lazy
	private EntityService entityService;
	
	@Autowired
	@Lazy
	private SeasonService seasonService;
	
	@Autowired
	@Lazy
	private EpisodeService episodeService;
	
	@Autowired
	@Lazy
	private GroupService groupService;
	
	//methods
	
	//get
		
	public ResponseEntity<Object> findAllMini(){
		try {
			Sort sort = Sort.by("release").descending();
			List<Post> posts = postRepository.findAll(sort);
			List<Object> objs = new ArrayList<>();
			for(Post post : posts) {
				if(post.getTypePost() == TypePost.UPDATE) {
					PostUpdateMiniDTO postUpdateMiniDTO = new PostUpdateMiniDTO((Update) post);
					objs.add(postUpdateMiniDTO);
				}else if(post.getTypePost() == TypePost.TALK_USER) {
					PostTalkMiniDTO postTalkMiniDTO = new PostTalkMiniDTO(post);
					objs.add(postTalkMiniDTO);
					
				}else if(post.getTypePost() == TypePost.QUEST) {
					PostQuestMiniDTO postQuestMiniDTO = new PostQuestMiniDTO((Quest) post);
					objs.add(postQuestMiniDTO);
				}
			
			}
			return ResponseEntity.ok().body(objs);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Object> findByIdMini(String idPost, String idUser){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Post post = postRepository.findById(idPost).get();
			if(post.getTypePost() == TypePost.UPDATE) {
				PostUpdateMiniDTO postUpdateMiniDTO = new PostUpdateMiniDTO((Update) post);
				if(post.getLikes().contains(user)) {
					postUpdateMiniDTO.setLiked(true);
				}else {
					postUpdateMiniDTO.setLiked(false);
				}
				if(!post.getLikes().isEmpty()) {
					UserMiniDTO userMiniDTO = new UserMiniDTO(post.getLikes().get(0));
					if(userMiniDTO.getId().hashCode() != idUser.hashCode()) {
						postUpdateMiniDTO.setLike(userMiniDTO);
					}else {
						if(post.getLikes().size() > 1) {
							userMiniDTO = new UserMiniDTO(post.getLikes().get(1));
							postUpdateMiniDTO.setLike(userMiniDTO);
						}
					}
				}
				return ResponseEntity.ok().body(postUpdateMiniDTO);
			}else if(post.getTypePost() == TypePost.TALK_USER) {
				PostTalkMiniDTO postTalkMiniDTO = new PostTalkMiniDTO(post);
				if(post.getLikes().contains(user)) {
					postTalkMiniDTO.setLiked(true);
				}else {
					postTalkMiniDTO.setLiked(false);
				}
				if(!post.getLikes().isEmpty()) {
					UserMiniDTO userMiniDTO = new UserMiniDTO(post.getLikes().get(0));
					if(userMiniDTO.getId().hashCode() != idUser.hashCode()) {
						postTalkMiniDTO.setLike(userMiniDTO);
					}else {
						if(post.getLikes().size() > 1) {
							userMiniDTO = new UserMiniDTO(post.getLikes().get(1));
							postTalkMiniDTO.setLike(userMiniDTO);
						}
					}
				}
				return ResponseEntity.ok().body(postTalkMiniDTO); 
			}else if(post.getTypePost() == TypePost.QUEST) {
				PostQuestMiniDTO postQuestMiniDTO = new PostQuestMiniDTO((Quest) post);
				if(post.getLikes().contains(user)) {
					postQuestMiniDTO.setLiked(true);
				}else {
					postQuestMiniDTO.setLiked(false);
				}
				if(!post.getLikes().isEmpty()) {
					UserMiniDTO userMiniDTO = new UserMiniDTO(post.getLikes().get(0));
					if(userMiniDTO.getId().hashCode() != idUser.hashCode()) {
						postQuestMiniDTO.setLike(userMiniDTO);
					}else {
						if(post.getLikes().size() > 1) {
							userMiniDTO = new UserMiniDTO(post.getLikes().get(1));
							postQuestMiniDTO.setLike(userMiniDTO);
						}
					}
				}
				return ResponseEntity.ok().body(postQuestMiniDTO); 
			}
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
		return null;
	}
	
	public ResponseEntity<Object> getPostAll(String id){
		try {
			User user = (User) userService.findById(id).getBody();
			List<Group> groups = user.getGroups();
			Sort sort = Sort.by("release").descending();
			List<Post> objs = postRepository.findAll(sort);
			List<Object> posts = new ArrayList<>();
			List<String> ids = new ArrayList<>();
			for(User following : user.getFollower().getFollowing()) {
				ids.add(following.getId());
			}
			int value = 0;
			for(Post post : objs) {
				if(post.getTypePostVisibility() == TypePostVisibility.USER) {
					if(ids.contains(post.getAuthor().getId()) || user.getId().hashCode() == post.getAuthor().getId().hashCode() ) {
						if(post.getTypePost() == TypePost.UPDATE) {
							PostUpdateMiniDTO postUpdateMiniDTO = new PostUpdateMiniDTO((Update) post);
							if(post.getLikes().contains(user)) {
								postUpdateMiniDTO.setLiked(true);
							}else {
								postUpdateMiniDTO.setLiked(false);
							}
							if(!post.getLikes().isEmpty()) {
								UserMiniDTO userMiniDTO = new UserMiniDTO(post.getLikes().get(0));
								if(userMiniDTO.getId().hashCode() != id.hashCode()) {
									postUpdateMiniDTO.setLike(userMiniDTO);
								}else {
									if(post.getLikes().size() > 1) {
										userMiniDTO = new UserMiniDTO(post.getLikes().get(1));
										postUpdateMiniDTO.setLike(userMiniDTO);
									}
								}
							}
							
							posts.add(postUpdateMiniDTO);
						}else if(post.getTypePost() == TypePost.TALK_USER) {
							PostTalkMiniDTO postTalkMiniDTO = new PostTalkMiniDTO(post);
							
							if(post.getLikes().contains(user)) {
								postTalkMiniDTO.setLiked(true);
							}else {
								postTalkMiniDTO.setLiked(false);
							}
							if(!post.getLikes().isEmpty()) {
								UserMiniDTO userMiniDTO = new UserMiniDTO(post.getLikes().get(0));
								if(userMiniDTO.getId().hashCode() != id.hashCode()) {
									postTalkMiniDTO.setLike(userMiniDTO);
								}else {
									if(post.getLikes().size() > 1) {
										userMiniDTO = new UserMiniDTO(post.getLikes().get(1));
										postTalkMiniDTO.setLike(userMiniDTO);
									}
								}
							}
							posts.add(postTalkMiniDTO);
						}else if(post.getTypePost() == TypePost.QUEST) {
							Quest quest = (Quest) post;
							PostQuestMiniDTO postQuestMiniDTO = new PostQuestMiniDTO((Quest) post);
							
							if(post.getLikes().contains(user)) {
								postQuestMiniDTO.setLiked(true);
							}else {
								postQuestMiniDTO.setLiked(false);
							}
							for(VoteQuest voteQuest : quest.getUsersVotes()) {
								if(voteQuest.getUser().equals(user)) {
									postQuestMiniDTO.setVoted(true);
									postQuestMiniDTO.setValueVoted(voteQuest.getVote());
								}
							}
							if(!post.getLikes().isEmpty()) {
								UserMiniDTO userMiniDTO = new UserMiniDTO(post.getLikes().get(0));
								if(userMiniDTO.getId().hashCode() != id.hashCode()) {
									postQuestMiniDTO.setLike(userMiniDTO);
								}else {
									if(post.getLikes().size() > 1) {
										userMiniDTO = new UserMiniDTO(post.getLikes().get(1));
										postQuestMiniDTO.setLike(userMiniDTO);
									}
								}
							}
							posts.add(postQuestMiniDTO);
						}
						
					}
					
				}else if(post.getTypePostVisibility() == TypePostVisibility.GROUP) {
					TalkGroup postTalkGroup =  (TalkGroup) post;
					if(groups.contains(postTalkGroup.getGroup())) {
						PostTalkGroupMiniDTO postTalkGroupMiniDTO = new PostTalkGroupMiniDTO(postTalkGroup);
						if(post.getLikes().contains(user)) {
							postTalkGroupMiniDTO.setLiked(true);
						}else {
							postTalkGroupMiniDTO.setLiked(false);
						}
						if(!post.getLikes().isEmpty()) {
							UserMiniDTO userMiniDTO = new UserMiniDTO(post.getLikes().get(0));
							if(userMiniDTO.getId().hashCode() != id.hashCode()) {
								postTalkGroupMiniDTO.setLike(userMiniDTO);
							}else {
								if(post.getLikes().size() > 1) {
									userMiniDTO = new UserMiniDTO(post.getLikes().get(1));
									postTalkGroupMiniDTO.setLike(userMiniDTO);
								}
							}
						}
						
						posts.add(postTalkGroupMiniDTO);
					}
				}
				value += value;
				if(value >= 500) {
					return ResponseEntity.ok().body(posts); 
				}
			}
			
			return ResponseEntity.ok().body(posts);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
		
	public ResponseEntity<Object> getCommentsMini(String idPost, String idUser){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Post post = postRepository.findById(idPost).get();
			List<Comment> comments = post.getComments();
			List<CommentMiniDTO> commentMiniDTOs = new ArrayList<>();
			for(Comment comment : comments) {
				CommentMiniDTO commentMiniDTO = new CommentMiniDTO(comment);
				if(comment.getLikes().contains(user)) {
					commentMiniDTO.setLiked(true);
				}else {
					commentMiniDTO.setLiked(false);
				}
				commentMiniDTOs.add(commentMiniDTO);
			}
			return ResponseEntity.ok().body(commentMiniDTOs);
		}catch (RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> getLikes(String id){
		try {
			Post post = postRepository.findById(id).get();
			List<User> likes = post.getLikes();
			List<UserMiniDTO> userMiniDTOs = new ArrayList<>();
			for(User user : likes) {
				UserMiniDTO userMiniDTO = new UserMiniDTO(user);
				userMiniDTOs.add(userMiniDTO);
			}
			return ResponseEntity.ok().body(userMiniDTOs);
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//post
	
	public ResponseEntity<Object> newPostUpdate(PostUpdateDTO postDTO){
		try {
			if(postDTO.getRelease() == null) {
				return ResponseEntity.badRequest().build();
			}
			User user = (User) userService.findById(postDTO.getIdAuthor()).getBody();
			Entity entity = (Entity) entityService.findById(postDTO.getIdEntity()).getBody();
			Season season = (Season) seasonService.findById(postDTO.getIdSeason()).getBody();
			Episode episode = (Episode) episodeService.findById(postDTO.getIdEpisode()).getBody();
			Update post = new Update(
					postDTO.getRelease(),
					postDTO.getBody(),
					postDTO.getTypePost(),
					postDTO.getTypePostVisibility(),
					user,
					postDTO.getSpoiler(),
					postDTO.getCategory(),
					postDTO.getLevel(),
					postDTO.getEvaluation(),
					entity,
					season,
					episode
					);
			Post obj = post;
			obj = postRepository.insert(obj);
			PostUser postUser = new PostUser(obj.getId(), obj.getTypePost());
			user.getPosts().add(postUser);
			userService.save(user);
			PostUpdateMiniDTO postUpdateMiniDTO = new PostUpdateMiniDTO(post);
			return ResponseEntity.created(null).body(postUpdateMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
		
		
	public ResponseEntity<Object> newPostTalkUser(PostTalkDTO postDTO){
		try {
			if(postDTO.getRelease() == null) {
				return ResponseEntity.badRequest().build();
			}
			User user = (User) userService.findById(postDTO.getIdAuthor()).getBody();
			Post post = new Post(postDTO.getRelease(), postDTO.getBody(), TypePost.TALK_USER, TypePostVisibility.USER, user, postDTO.getSpoiler());
			post = postRepository.insert(post);
			PostUser postUser = new PostUser(post.getId(), post.getTypePost());
			user.getPosts().add(postUser);
			userService.save(user);
			PostTalkMiniDTO postTalkMiniDTO = new PostTalkMiniDTO(post);
			return ResponseEntity.created(null).body(postTalkMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> newPostTalkGroup(PostTalkGroupDTO postDTO){
		try {
			if(postDTO.getRelease() == null) {
				return ResponseEntity.badRequest().build();
			}
			User user = (User) userService.findById(postDTO.getIdAuthor()).getBody();
			Group group = groupService.findById(postDTO.getIdGroup());
			TalkGroup post = new TalkGroup(postDTO.getRelease(), postDTO.getBody(), TypePost.TALK_GROUP, TypePostVisibility.GROUP, user, postDTO.getSpoiler(), false, null, group);
			post = postRepository.insert(post);
			PostUser postUser = new PostUser(post.getId(), post.getTypePost());
			user.getPosts().add(postUser);
			userService.save(user);
			group.getPosts().add(post);
			groupService.save(group);
			PostTalkGroupMiniDTO postTalkGroupMiniDTO = new PostTalkGroupMiniDTO(post);
			return ResponseEntity.created(null).body(postTalkGroupMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> newPostQuest(PostQuestDTO postDTO){
		try {
			if(postDTO.getRelease() == null) {
				return ResponseEntity.badRequest().build();
			}
			User user = (User) userService.findById(postDTO.getIdAuthor()).getBody();
			Post post = new Quest(postDTO.getRelease(), postDTO.getBody(), TypePost.QUEST, postDTO.getTypePostVisibility(), user, postDTO.getSpoiler(), postDTO.getOptions(), 0, postDTO.getVotes());
			post = postRepository.insert(post);
			PostUser postUser = new PostUser(post.getId(), post.getTypePost());
			user.getPosts().add(postUser);
			userService.save(user);
			PostTalkMiniDTO postTalkMiniDTO = new PostTalkMiniDTO(post);
			return ResponseEntity.created(null).body(postTalkMiniDTO);
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	//delete
	
	public ResponseEntity<Object> deletePost(String idPost, String idUser){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Post post = (Post) postRepository.findById(idPost).get();
			if(user.getId().hashCode() != post.getAuthor().getId().hashCode()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			PostUser postUser = new PostUser(post.getId(), post.getTypePost());
			user.getPosts().remove(postUser);
			userService.save(user);
			postRepository.delete(post);
			return ResponseEntity.ok().build();
		}catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	//put
	
	public ResponseEntity<Object> addBodyUpdatePost(PostUpdateDTO postUpdateDTO){
		try {
			User user = (User) userService.findById(postUpdateDTO.getIdAuthor()).getBody();
			Post post = (Update) postRepository.findById(postUpdateDTO.getIdPost()).get();
			
			if(!(user.getId().hashCode() == post.getAuthor().getId().hashCode())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			post.setBody(postUpdateDTO.getBody());
			post.setSpoiler(postUpdateDTO.getSpoiler());
			postRepository.save(post);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> updateVotePostQuest(int value, String idUser, String idPost){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Quest post = (Quest) postRepository.findById(idPost).get();
			for(VoteQuest voteQuest :  post.getUsersVotes()) {
				if(voteQuest.getUser().equals(user)) {
					if(value != voteQuest.getVote()) {
						//post.setVotesQuantity(+1);
						post.getVotes().add(voteQuest.getVote(), post.getVotes().get(value) - 1);
						post.getVotes().add(value, post.getVotes().get(value) + 1);
						voteQuest = new VoteQuest(post, user, value);
						post.getUsersVotes().add(voteQuest);
						postRepository.save(post);
						return ResponseEntity.accepted().build();
					}else {
						post.setVotesQuantity(-1);
						post.getVotes().add(value, post.getVotes().get(value) - 1);
						post.getUsersVotes().remove(voteQuest);
						postRepository.save(post);
						return ResponseEntity.accepted().build();
					}
				}
			}
			post.setVotesQuantity(+1);
			post.getVotes().add(value, post.getVotes().get(value) + 1);
			VoteQuest voteQuest = new VoteQuest(post, user, value);
			post.getUsersVotes().add(voteQuest);
			postRepository.save(post);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> addLike(String idUser, String idPost){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Post post = postRepository.findById(idPost).get();
			List<User> users = post.getLikes();
			if(users.contains(user)) {
				return removeLike(idUser, idPost);
			}
			post.getLikes().add(user);
			post.setLikeQuantity(1);
			postRepository.save(post);
			LikeUser like = new LikeUser(post.getId(), TypeObject.POST);
			user.getLikes().add(like);
			userService.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> removeLike(String idUser, String idPost){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Post post = postRepository.findById(idPost).get();
			post.getLikes().remove(user);
			post.setLikeQuantity(-1);
			postRepository.save(post);
			LikeUser like = new LikeUser(post.getId(), TypeObject.POST);
			user.getLikes().remove(like);
			userService.save(user);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<Object> closeTalkGroup(String idUser, String idPost, String idGroup){
		try {
			User user = (User) userService.findById(idUser).getBody();
			Group group = groupService.findById(idGroup);
			TalkGroup post = (TalkGroup) postRepository.findById(idPost).get();
			if(!post.getTypePost().equals(TypePost.TALK_GROUP)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			if(!group.getModerators().contains(user) && !group.getCreator().equals(user)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			post.setClose(true);
			post.setClosedBy(user);
			postRepository.save(post);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	//internal
	
	public ResponseEntity<Object> findById(String id){
		try {
			Post post = postRepository.findById(id).get();
			return ResponseEntity.ok().body(post);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> save(Post post){
		try {
			postRepository.save(post);
			return ResponseEntity.accepted().build();
		}catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
}
