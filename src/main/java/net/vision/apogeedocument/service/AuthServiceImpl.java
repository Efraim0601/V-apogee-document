package net.vision.apogeedocument.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.vision.apogeedocument.dto.SignupRequest;
import net.vision.apogeedocument.entities.User;
import net.vision.apogeedocument.repositories.UserRepository;
import net.vision.apogeedocument.security.EmailValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ValidationService validationService;
    private EmailValidator emailValidator ;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public boolean createUser(SignupRequest signupRequest) {

        this.emailValidator = new EmailValidator();
        passwordEncoder = new BCryptPasswordEncoder();

        //let's verify if email is typed correctly
        if (this.emailValidator.validateEmail(signupRequest.getEmail())) {
            System.out.println("L'adresse e-mail est valide !");
            log.info("your mail is correct !!!:");
        } else {
            log.info("your email is wrong !!!!");
        }


        // vérifier si User est un employé à l'entreprise
        /*String key_word_email = " @gmail.com";
        contain_key_word(key_word_email, signupRequest);*/
        //************* à insérer dans une variable*****************




        // vérification de la présence d'un email dans la base de donnée
        Optional<User> userOptional = this.userRepository.findByEmail(signupRequest.getEmail());
        if (userOptional.isPresent()){
            throw new RuntimeException("Email already used!!! ");
        }

        //enregistrement de l'utilisateur et vérification de la présence d'un utilisateur dans la base de donnée
        Optional<User> userInDatabase = this.userRepository.findByEmail(signupRequest.getEmail());


        User user = new User();




        BeanUtils.copyProperties(signupRequest, user);
        String hashPassword = "";

        //Hash the password before saving
        if (signupRequest.getPassword().length() >= 8) {
            hashPassword = passwordEncoder.encode(signupRequest.getPassword());
            user.setPassword(hashPassword);
        }



        user.setNumero_employe(signupRequest.getNumero_employe());
        if(userInDatabase.isEmpty()){

            user.setEmail(signupRequest.getEmail());
            user.setNumero(signupRequest.getNumero());
            user.setLastName(signupRequest.getLastName());
            user.setFirstName(signupRequest.getFirstName());
            user.setNumero_employe(signupRequest.getNumero_employe());
            //User saveUser = this.userRepository.save(user);
            this.validationService.keppValidation(user);
        }


        return true;
    }

    private void contain_key_word(String s, SignupRequest signupRequest) {
        if(!signupRequest.getEmail().contains(s)){
            throw new RuntimeException("ce n'est pas un Email de l'entreprise!!! ");

        }
    }
}
