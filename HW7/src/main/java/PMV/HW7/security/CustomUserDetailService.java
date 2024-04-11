package PMV.HW7.security;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import PMV.HW7.entity.Person;
import PMV.HW7.repository.PersonRepository;


import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Ищем " + username);
        Person person = personRepository.findByLogin(username).orElseThrow(() ->
                new UsernameNotFoundException("Пользователь " + username + " не найден"));
        System.out.println("нашли " + person);
        return new User(person.getLogin(), person.getPassword(), List.of(
                new SimpleGrantedAuthority(person.getRole())
        ));
    }
}
