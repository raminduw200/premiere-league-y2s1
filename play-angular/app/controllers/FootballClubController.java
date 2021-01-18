package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.FootballClub;
import entities.FootballMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.FootballClubService;

import java.io.IOException;
import java.util.List;


/// template URL : https://github.com/dilum1995/IIT-PlayFramework-Session
public class FootballClubController extends Controller {

    private static final Logger fCLogger = LoggerFactory.getLogger("controller");

    public FootballClubController(){
        try {
            FootballClubService.readStorage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // return list of default football clubs
    public Result listDefaultFootballClubs() {
        List<FootballClub> defaultClubList = FootballClubService.getService().getAllDefaultFootballClubs();
        fCLogger.debug("FootballClubController.listDefaultFootballClubs() : {}",defaultClubList.toString());
        ObjectMapper objMapper = new ObjectMapper();

        return ok(objMapper.convertValue(defaultClubList, JsonNode.class));
    }

    // return list of university football clubs
    public Result listUniFootballClubs() {
        List<FootballClub> uniClubList = FootballClubService.getService().getAllUniFootballClubs();
        fCLogger.debug("FootballClubController.listUniFootballClubs() : {}",uniClubList.toString());
        ObjectMapper objMapper = new ObjectMapper();

        return ok(objMapper.convertValue(uniClubList, JsonNode.class));
    }

    // return list of school football clubs
    public Result listSchoolFootballClubs() {
        List<FootballClub> schoolClubList = FootballClubService.getService().getAllSchoolFootballClubs();
        fCLogger.debug("FootballClubController.listSchoolFootballClubs() : {}",schoolClubList.toString());
        ObjectMapper objMapper = new ObjectMapper();

        return ok(objMapper.convertValue(schoolClubList, JsonNode.class));
    }

    // return list of all football matches
    public Result listFootballMatches() {
        List<FootballMatch> allClubList = FootballClubService.getService().getAllFootballMatches();
        fCLogger.debug("FootballClubController.listFootballMatches() : {}",allClubList.toString());
        ObjectMapper objMapper = new ObjectMapper();

        return ok(objMapper.convertValue(allClubList, JsonNode.class));
    }

    // return randomly created match
    public Result createMatch() {
        FootballMatch footballMatch = FootballClubService.getService().createRandomMatch();
        fCLogger.debug("FootballClubController.createMatch() : {}",footballMatch.toString());
        ObjectMapper objMapper = new ObjectMapper();

        return ok(objMapper.convertValue(footballMatch, JsonNode.class));
    }
}
