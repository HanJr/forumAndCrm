package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import entity.Member;

public class MemberDao {

	JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//what happens if the email does not exist? Not catching here. 
	public Member selectByEmail(String email) {
		
		try {
		Member member = jdbcTemplate.queryForObject("select * from member where email = ?", new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member(rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("NAME"), rs.getTimestamp("REGDATE").toLocalDateTime());
				
				member.setId(rs.getString("ID"));
				return member;
			}
			
		}, email);
		
		return member;
		
		}catch(EmptyResultDataAccessException e) {
			return null;
		}	
	}
	
	//what happens if the email already exists in the table?
	public void insert(Member member) {
		KeyHolder keyholder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO MEMBER(EMAIL, PASSWORD, NAME, REGDATE) VALUES(?, ?, ?, ?)", new String[] {"ID"});
				
				preparedStatement.setString(1, member.getEmail());
				preparedStatement.setString(2, member.getPassword());
				preparedStatement.setString(3, member.getName());
				preparedStatement.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				
				return preparedStatement;
			}
			
		}, keyholder);
		
		member.setId(String.valueOf(keyholder.getKey()));
	}
	
	public void update(Member member) {
		jdbcTemplate.update("UPDATE MEMBER SET PASSWORD = ?, NAME = ? WHERE EMAIL = ?", member.getPassword(), member.getName(), member.getEmail());
		
	}
	
	public Collection<Member> selectAll(){
		Collection<Member> members = jdbcTemplate.query("select * from member",
				(ResultSet rs, int rowNum) ->{
					Member member = new Member(rs.getString("EMAIL"), rs.getString("PASSWORD"), 
							rs.getString("NAME"), rs.getTimestamp("REGDATE").toLocalDateTime());
					
					member.setId(String.valueOf(rs.getLong("ID")));
					return member;
				});
		
		
		return members;
	}
	
}
