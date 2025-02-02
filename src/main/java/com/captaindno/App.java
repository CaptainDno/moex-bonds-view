package com.captaindno;

import com.captaindno.types.Bond;
import com.captaindno.types.YieldInfo;

import javax.management.modelmbean.XMLParseException;
import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class App implements Runnable {

    List<Bond> bonds;
    HashMap<String, Bond> bondMap;

    static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public App() {

    }

    private void updateBondMap(HashMap<String, Bond> bondMap) {
        this.bondMap = bondMap;
        this.bonds = new ArrayList<>(bondMap.values());
    }

    public String handleCommand(String command) throws ParseException, IOException, XMLStreamException, XMLParseException {
        String[] parsed = command.split(" ");

        if (parsed[0].equals("load")) {
            updateBondMap(Parser.parseFromURI(URI.create(parsed[1]).toURL()));
            return "Загрузка успешно завершена";
        } else if (bondMap == null){
            return "Сначала загрузите данные командой load";
        }

        if (parsed[0].equals("order_by")) {
            boolean isDescending = parsed[2].equals("desc");
            Comparator<Bond> comparator;
            switch (parsed[1]) {
                case "maturity_date":
                    comparator = Comparator.comparing(Bond::getMaturityDate);
                    break;
                case "yield_current":
                    comparator = Comparator.comparing((b) -> {
                        YieldInfo i = b.getYieldInfo();
                        return i == null ? Double.MIN_VALUE : i.getYield();
                    });
                    break;
                case "yield_avg":
                    comparator = Comparator.comparing((b) -> {
                        YieldInfo i = b.getYieldInfo();
                        return i == null ? Double.MIN_VALUE : i.getYieldAtWAPrice();
                    });
                    break;
                default:
                    return "Неподдерживаемая команда";
            }
            bonds.sort(isDescending ? comparator.reversed() : comparator);
            System.out.println();
            return "Список отсортирован";
        }

        if (parsed[0].equals("reset")) {
            this.bonds = new ArrayList<>(bondMap.values());
            return "Список восстановлен";
        }

        if (parsed[0].equals("filter")) {

            switch (parsed[1]) {
                case "yield":
                    switch (parsed[2]){
                        case "known":
                            bonds.removeIf(b -> b.getYieldInfo() == null);
                            break;
                        case "unknown":
                            bonds.removeIf(b -> b.getYieldInfo() != null);
                            break;
                        case ">=":
                            double min = Double.parseDouble(parsed[3]);
                            bonds.removeIf(b -> {
                                YieldInfo i = b.getYieldInfo();
                                return i == null || (i.getYield() < min && i.getYieldAtWAPrice() < min);
                            });
                            break;
                    }
                    break;
                case "maturity_date":
                    Date d;
                    switch (parsed[2]){
                        case "before":
                            d = dateFormat.parse(parsed[3]);
                            bonds.removeIf(b -> b.getMaturityDate().after(d));
                            break;
                        case "after":
                            d = parsed[3].equals("today") ? new Date() : dateFormat.parse(parsed[3]);
                            bonds.removeIf(b -> b.getMaturityDate().before(d));
                            break;
                    }
                    break;
            }
            return "Список отфильтрован";
        }

        if (parsed[0].equals("show")) {
            boolean startFromEnd = parsed[1].equals("last");
            int limit = Integer.parseInt(parsed[2]);
            int start = 0;
            if (parsed.length > 3 && parsed[3].equals("skip")) {
                start = Integer.parseInt(parsed[4]);
            }
            return (startFromEnd ? bonds.reversed() : bonds).subList(start, start + limit).stream().map(Bond::toString).collect(Collectors.joining());
        }
        return "Неподдерживаемая команда";
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (;;) {
            System.out.print(">>> ");
            try {
                String command = reader.readLine();
                if (command == null || command.equals("quit")) {
                    break;
                }
                System.out.println(handleCommand(command));

            } catch (IOException | ParseException | XMLStreamException | XMLParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
