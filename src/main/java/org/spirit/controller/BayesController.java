package org.spirit.controller;

import org.spirit.model.entity.Lesson;
import org.spirit.model.entity.User;
import org.spirit.service.BayesService;
import org.spirit.service.LessonService;
import org.spirit.service.UserService;
import org.eclipse.recommenders.jayes.BayesNet;
import org.eclipse.recommenders.jayes.BayesNode;
import org.eclipse.recommenders.jayes.inference.IBayesInferer;
import org.eclipse.recommenders.jayes.inference.jtree.JunctionTreeAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bayes")
public class BayesController {

    @Autowired
    private BayesService bayesService;

    @Autowired
    private UserService userService;

    @Autowired
    private LessonService lessonService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("lessons", lessonService.getAll());
        return "bayes/index";
    }

    @RequestMapping(value ="/result", method = RequestMethod.POST)
    public String result(@RequestParam("user") Long userId, @RequestParam("lesson") Long lessonId, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("lessons", lessonService.getAll());
        BayesNet net = bayesService.createNetwork();
        IBayesInferer inferer = new JunctionTreeAlgorithm();

        Lesson parent = lessonService.getParent(lessonId);
        if (parent == null) {
            model.addAttribute("result", "Podana lekcja nie ma poprzednika");
            return "bayes/index";
        }
        Lesson current = lessonService.get(lessonId);
        User user = userService.getUser(userId);

        String note = bayesService.getNote(userId, lessonId);
        System.out.println(note);
        if (note == null || note.equals("0")) {
            model.addAttribute("result", "Brak ocen dla poprzedniej lekcji");
            return "bayes/index";
        }
        inferer.setNetwork(net);
        Map<BayesNode,String> e = new HashMap<BayesNode,String>();
        e.put(net.getNode(parent.getId().toString()), note);
        inferer.setEvidence(e);

        double[] result = inferer.getBeliefs(net.getNode(lessonId.toString()));
        String resultString = "";
        for (int i = 0; i<result.length; ++i) {
            int grade = i+1;
            resultString += "ocena " + grade + " - " + result[i] + ",";
        }
        model.addAttribute("user", user);
        model.addAttribute("current", current);
        model.addAttribute("parent", parent);
        model.addAttribute("note", note);
        model.addAttribute("result", resultString);
        return "bayes/index";

    }
}
