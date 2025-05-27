package ru.bmstu.iu7.impl;

import ru.bmstu.iu7.API.model.*;
import ru.bmstu.iu7.impl.model.*;

import java.util.ArrayList;
import java.util.List;

public class ModelFactory {
    public static IExtendedAnswer makeExtendedAnswer(Long id, IQuestion question,
                                              int weight, String answer, List<ITag> tags) {
        ExtendedAnswer ans = new ExtendedAnswer();
        ans.setId(id);
        ans.setQuestion(question);
        ans.setWeight(weight);
        ans.setAnswer(answer);
        ans.setTags(tags);
        return ans;
    }

    public static IInformation makeInformation(Long id, List<IVariantAnswer> variantAnswers,
                                        List<IExtendedAnswer> extendedAnswers) {
        Information inf = new Information();
        inf.setId(id);
        inf.setVariantAnswers(variantAnswers);
        inf.setExtendedAnswers(extendedAnswers);
        return inf;
    }

    public static IQuestion makeQuestion(Long id, String question, List<ITag> tags, boolean kind) {
        Question q = new Question();
        q.setId(id);;
        q.setQuestion(question);
        q.setTags(tags);
        q.setKind(kind);
        return q;
    }

    public static IQuestionnaire makeQuestionnaire(IUser user, IInformation information, IInformation searchInformation)
    {
        Questionnaire q = new Questionnaire();
        q.setBlackList( new ArrayList<>());
        q.setFavList( new ArrayList<>());
        q.setUser( user );
        q.setInformation( information );
        q.setSearchInformation( searchInformation );
        return q;
    }

    public static ITag makeTag(Long id, String name) {
        Tag t = new Tag();
        t.setId(id);
        t.setName(name);
        return t;
    }

    public static IUser makeUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return user;
    }

    public static IVariantAnswer makeVariantAnswer(Long id, int weight, ITag tag, IQuestion question){
        VariantAnswer answer = new VariantAnswer();
        answer.setId(id);
        answer.setWeight(weight);
        answer.setTag(tag);
        answer.setQuestion(question);
        return answer;
    }



}
