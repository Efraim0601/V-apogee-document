package net.vision.apogeedocument.service;

import lombok.extern.slf4j.Slf4j;
import net.vision.apogeedocument.dto.SignupRequest;
import net.vision.apogeedocument.entities.User;
import net.vision.apogeedocument.entities.Validation;
import net.vision.apogeedocument.repositories.UserRepository;
import net.vision.apogeedocument.security.EmailValidator;
import net.vision.apogeedocument.service.jwt.UserServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserServiceImpl userServiceImpl;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public boolean createUser(User user) {

        this.emailValidator = new EmailValidator();
        passwordEncoder = new BCryptPasswordEncoder();

        //let's verify if email is typed correctly
        if (this.emailValidator.validateEmail(user.getEmail())) {
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
        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()){
            throw new RuntimeException("Email already used!!! ");
        }

        //enregistrement de l'utilisateur et vérification de la présence d'un utilisateur dans la base de donnée
        Optional<User> userInDatabase = this.userRepository.findByEmail(user.getEmail());







        BeanUtils.copyProperties(user, user);
        String hashPassword = "";

        //Hash the password before saving
        if (user.getPassword().length() >= 8) {
            hashPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashPassword);
        }



        //user.setNumeroempl(user.getNumeroempl());
        if(userInDatabase.isEmpty()){

           /* user.setEmail(user.getEmail());
            user.setNumero(user.getNumero());
            user.setLastName(user.getLastName());
            user.setFirstName(signupRequest.getFirstName());
            user.setNumeroempl(signupRequest.getNumeroempl());*/

            this.userRepository.save(user);
            this.validationService.keppValidation(user);
        }


        return true;
    }

    private void contain_key_word(String s, SignupRequest signupRequest) {
        if(!signupRequest.getEmail().contains(s)){
            throw new RuntimeException("ce n'est pas un Email de l'entreprise!!! ");

        }


    }
    /**
     *
     * Get all users
     *
     *
     */
    public List<User> search(){

        return (List<User>) this.userRepository.findAll();
    }

    /**
     *
     * get all user by id
     *
     *
     */

    public User getUser(int id) {
        Optional<User> optionalUser = this.userRepository.findByNumeroempl(id);
        return optionalUser.orElse(null);

    }

    /**
     *
     * Gestion du code d'activation
     *
     *
     */

    public void activation(Map<String, String> activation) {
        Validation validation = this.validationService.getByCode(activation.get("code"));

        if(Instant.now().isAfter(validation.getExpire())){
            throw  new RuntimeException("Your activation key has expired");
        }
        User activeuser = this.userRepository.findById(validation.getUser().getNumeroempl()).orElseThrow(()->new RuntimeException("Unknown user !!!"));
        activeuser.setActive(true);
        validation.setActivation(Instant.now());
        this.userRepository.save(activeuser);
    }

   /* @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) this.userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("no user matched !!!"));

    }*/

    public void changePassword(Map<String, String> args) {
        User user = (User) this.userServiceImpl.loadUserByUsername(args.get("email"));
        this.validationService.keppValidation(user);
    }

    public void newPassword(Map<String, String> args) {
        User user = (User) this.userServiceImpl.loadUserByUsername(args.get("email"));
        final Validation validation = validationService.getByCode(args.get("code"));
        if(validation.getUser().getEmail().equals(user.getEmail())){
            String cryptPassword = this.passwordEncoder.encode(args.get("password"));
            user.setPassword(cryptPassword);
        }

    }
}
