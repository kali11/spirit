package org.spirit.service.impl;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import org.spirit.model.dao.LessonDao;
import org.spirit.model.dao.TestResultDao;
import org.spirit.model.entity.Lesson;
import org.spirit.model.entity.TestResult;
import org.spirit.service.BayesService;
import org.eclipse.recommenders.jayes.BayesNet;
import org.eclipse.recommenders.jayes.BayesNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BayesServiceImpl implements BayesService {

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private TestResultDao testResultDao;

    @Override
    public BayesNet createNetwork() {
        BayesNet net = new BayesNet();
        List<Lesson> lessons = lessonDao.findAll();
        lessons.forEach(l -> {
            BayesNode node = net.createNode(l.getId().toString());
            node.addOutcomes("1", "2", "3", "4", "5");
        });
        lessons.forEach(l -> {
            BayesNode node = net.getNode(l.getId().toString());
            if (l.getPreds() != null) {
                String pred = l.getPreds();
                node.setParents(Arrays.asList(net.getNode(pred)));
                node.setProbabilities(getProbabilities(l));

            } else {
                node.setProbabilities(0.2, 0.2, 0.2, 0.2, 0.2);
            }
        });
        return net;
    }

    @Override
    public String getNote(Long userId, Long lessonId) {
        Search search = new Search();
        search.addFilter(Filter.equal("user.id", userId));
        search.addFilter(Filter.equal("elementTest.lesson.id", lessonId));
        List<TestResult> testResults = testResultDao.search(search);
        double score = 0.0;
        double max = 0.0;
        for(TestResult t : testResults) {
            score += t.getScore();
            max += t.getMaxScore();
        }

        return new Long(Math.round((score / max * 5) + 0.5)).toString();
    }

    public double[] getProbabilities(Lesson lesson) {
        Search search = new Search();
        search.addFilter(Filter.equal("elementTest.lesson", lesson));
        List<TestResult> testResults = testResultDao.search(search);

        Lesson parent = lessonDao.find(Long.valueOf(lesson.getPreds()));
        search = new Search();
        search.addFilter(Filter.equal("elementTest.lesson", parent));
        List<TestResult> testResultsParent = testResultDao.search(search);

        Map<Long, Row> rowsMap = getRowsMap(testResults, testResultsParent);
        System.out.println(rowsMap.toString());

        double[] result = new double[25];
        for (Row r : rowsMap.values()) {
            int p = r.scoreParent.intValue();
            int c = r.scoreParent.intValue();
            result[5 * (p - 1) + (c-1)]++;
        }
        for (int i =0; i < 25; i+=5) {
            double sum = result[i] + result[i+1] + result[i+2] + result[i+3] + result[i+4];
            if (sum != 0.0) {
                result[i] = result[i] / sum;
                result[i + 1] = result[i + 1] / sum;
                result[i + 2] = result[i + 2] / sum;
                result[i + 3] = result[i + 3] / sum;
                result[i + 4] = result[i + 4] / sum;
            }
        }
        return result;
    }

    private Map<Long, Row> getRowsMap(List<TestResult> testResults, List<TestResult> testResultsParent) {
        Map<Long, Row> rowsMap = new HashMap<>();

        for(TestResult t : testResults) {
            Row row = rowsMap.get(t.getUser().getId());
            if (row == null) {
                Row r = new Row();
                r.userId = t.getUser().getId();
                r.scoreCurrent += t.getScore();
                r.maxScoreCurrent += t.getMaxScore();
                rowsMap.put(t.getUser().getId(), r);
            } else {
                row.userId = t.getUser().getId();
                row.scoreCurrent += t.getScore();
                row.maxScoreCurrent += t.getMaxScore();
            }
        }

        for (TestResult t : testResultsParent) {
            Row r = rowsMap.get(t.getUser().getId());
            if (r != null) {
                r.scoreParent += t.getScore();
                r.maxScoreParent += t.getMaxScore();
            }
        }

        for(Row r : rowsMap.values()) {
            r.scoreCurrent = new Long(Math.round((r.scoreCurrent / r.maxScoreCurrent * 5) + 0.5)).doubleValue();
            r.scoreParent = new Long(Math.round((r.scoreParent / r.maxScoreParent * 5) + 0.5)).doubleValue();
        }

        return rowsMap;
    }

    public class Row {
        public Long userId;
        public Double scoreCurrent;
        public Double scoreParent;
        public Integer maxScoreCurrent;
        public Integer maxScoreParent;

        public Row(){
            scoreCurrent = scoreParent = 0.0;
            maxScoreCurrent = maxScoreParent = 0;
        }

        @Override
        public String toString(){
            return "scoreCurrent: " + scoreCurrent + " scoreParent: " + scoreParent;
        }
    }

}
