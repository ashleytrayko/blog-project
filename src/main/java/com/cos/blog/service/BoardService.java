package com.cos.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void write(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly=true)
	public Page<Board> list(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly=true)
	public Board detail(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});
	}
	
	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void update(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
				}); //영속화 완료 
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수 종료시(서비스가 종료될 때) 트랜잭션이 종료됨. 이때 더티체킹 -> 자동업데이트 db flush
	}
//	@Transactional(readOnly = true) //select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성) 
//	public User login(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	
	@Transactional
	public void writeReply(ReplySaveRequestDto replySaveRequestDto) {
		User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패 : 유저 id를 찾을 수 없습니다.");
		});

		Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
		});
		
//		Reply reply = Reply.builder()
//				.user(user)
//				.board(board)
//				.content(replySaveRequestDto.getContent())
//				.build();
		
		Reply reply = new Reply();
		reply.update(user, board, replySaveRequestDto.getContent());
		
		replyRepository.save(reply);
		
		// 네이밍 쿼리
		//replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
	}
}
