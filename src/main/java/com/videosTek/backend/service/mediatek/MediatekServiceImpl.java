package com.videosTek.backend.service.mediatek;

import com.videosTek.backend.entity.Mediatek;
import com.videosTek.backend.entity.User;
import com.videosTek.backend.repository.MediatekRepository;
import com.videosTek.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Media;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class MediatekServiceImpl implements MediatekService {

    private final MediatekRepository repository;
    private final UserRepository userRepository;


    @Autowired
    public MediatekServiceImpl(
            MediatekRepository repository,
            AuthenticationManager authenticationManager,
            UserRepository userRepository) {
        this.repository = repository;

        this.userRepository=userRepository;
    }

    @Override
    public Optional<Mediatek> findById(UUID id) {
        return repository.findById(id);
    }

    public Mediatek save(Mediatek mediatek){
        return this.repository.save(mediatek);

    }

    public void addMovie(Long movieId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            Optional<User> optU =this.userRepository.findByEmail(authentication.getName());
            if(optU.isPresent()){
                User u =optU.get();
                Optional<Mediatek> optM = this.repository.findById(u.getId());
                if(optM.isPresent()){
                    Mediatek m = optM.get();
                    if(m.getMovieIdList().isEmpty()) {
                            m.setMovieIdList(movieId.toString());
                    }else {
                        if (!m.getMovieIdList().contains(movieId.toString()))
                            m.setMovieIdList(m.getMovieIdList() + "," + movieId.toString());
                    }
                    this.repository.save(m);
                }
            }
        }
    }

    public Boolean ifMediatekContain(Long movieId,UUID id) {
            Optional<Mediatek> optM = this.repository.findById(id);
            if(optM.isPresent()){
                String l = optM.get().getMovieIdList();
                if(!l.isEmpty()){
                    List<String> ll= Arrays.stream(l.split(",")).toList();
                    return ll.contains(movieId.toString());
                }else{
                    return false;
                }
            }else{
                throw new RuntimeException("Not find movielist element");
            }

    }
}
