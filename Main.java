import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;

class Candidate {
    private int id;
    private String name;

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }
}

class Voter {
    private int id;
    private String name;

    public Voter(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }
}

class VotingSystem {
    private ArrayList<Candidate> candidates;
    private ArrayList<Voter> voters;

    public VotingSystem() {
        candidates = new ArrayList<>();
        voters = new ArrayList<>();
    }

    public void loadCandidates(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                candidates.add(new Candidate(id, name));
            }
        } catch (IOException e) {
            System.err.println("Error reading candidate file: " + e.getMessage());
        }
    }

    public void loadVoters(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                voters.add(new Voter(id, name));
            }
        } catch (IOException e) {
            System.err.println("Error reading voter file: " + e.getMessage());
        }
    }

    public void registerCandidate(int id, String name, String fileName) {
        Candidate candidate = new Candidate(id, name);
        candidates.add(candidate);
        saveCandidates(fileName);
        System.out.println("Candidate registered successfully.");
    }

    public void updateCandidate(int CandidateId, String updateCandidateName) {

        // Search for the candidate by ID and update their name
        boolean candidateFound = false;
        for (Candidate candidate : candidates) {
            if (candidate.getId() == CandidateId) {
                candidate.setName(updateCandidateName);
                candidateFound = true;
                break;
            }
        }

        // Write the updated candidate list back to the file
        if (candidateFound) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("candidates.txt"))) {
                for (Candidate candidate : candidates) {
                    bw.write(candidate.getId() + "," + candidate.getName());
                    bw.newLine();
                }
                System.out.println(
                        "Candidate with ID " + CandidateId + " Updated with New Candiate Name " + updateCandidateName
                                + " updated successfully.");
            } catch (IOException e) {
                System.err.println("Error writing to candidate file: " + e.getMessage());
            }
        } else {
            System.out.println("Candidate with ID " + CandidateId + " not found.");
        }
    }

    public void deleteCandidate(int id, String fileName) {
        Candidate candidateToRemove = null;
        for (Candidate candidate : candidates) {
            if (candidate.getId() == id) {
                candidateToRemove = candidate;
                break;
            }
        }
        if (candidateToRemove != null) {
            candidates.remove(candidateToRemove);
            saveCandidates(fileName);
            System.out.println("Candidate deleted successfully.");
        } else {
            System.out.println("Candidate not found.");
        }
    }

    public void viewCandidates() {
        System.out.println("List of Candidates:");
        for (Candidate candidate : candidates) {
            System.out.println(candidate);
        }
    }

    public void registerVoter(int id, String name, String fileName) {
        Voter voter = new Voter(id, name);
        voters.add(voter);
        saveVoters(fileName);
        System.out.println("Voter registered successfully.");
    }

    public void updateVoter(int VoterId, String updateVoterName) {
        // Search for the voter by ID and update their name
        boolean voterFound = false;
        for (Voter voter : voters) {
            if (voter.getId() == VoterId) {
                voter.setName(updateVoterName);
                voterFound = true;
                break;
            }
        }

        // Write the updated voter list back to the file
        if (voterFound) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("voters.txt"))) {
                for (Voter voter : voters) {
                    bw.write(voter.getId() + "," + voter.getName());
                    bw.newLine();
                }
                System.out.println(
                        "Voter with ID " + VoterId + " Updated with New Voter Name " + updateVoterName
                                + " updated successfully.");
            } catch (IOException e) {
                System.err.println("Error writing to voter file: " + e.getMessage());
            }
        } else {
            System.out.println("Voter with ID " + VoterId + " not found.");
        }

    }

    public void deleteVoter(int id, String fileName) {
        Voter voterToRemove = null;
        for (Voter voter : voters) {
            if (voter.getId() == id) {
                voterToRemove = voter;
                break;
            }
        }
        if (voterToRemove != null) {
            voters.remove(voterToRemove);
            saveVoters(fileName);
            System.out.println("Voter deleted successfully.");
        } else {
            System.out.println("Voter not found.");
        }
    }

    public void viewVoters() {
        System.out.println("List of Voters:");
        for (Voter voter : voters) {
            System.out.println(voter);
        }
    }

    private void saveCandidates(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Candidate candidate : candidates) {
                bw.write(candidate.getId() + "," + candidate.getName());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to candidate file: " + e.getMessage());
        }
    }

    private void saveVoters(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Voter voter : voters) {
                bw.write(voter.getId() + "," + voter.getName());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to voter file: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        VotingSystem votingSystem = new VotingSystem();
        votingSystem.loadCandidates("candidates.txt");
        votingSystem.loadVoters("voters.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect User Type:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int userType = scanner.nextInt();

            if (userType == 1) {
                // Admin Menu
                System.out.println("\nAdmin Menu:");
                System.out.println("1. Register Candidate");
                System.out.println("2. Update Candidate");
                System.out.println("3. Delete Candidate");
                System.out.println("4. View List of Candidates");
                System.out.println("5. Register Voter");
                System.out.println("6. Update Voter");
                System.out.println("7. Delete Voter");
                System.out.println("8. View List of Voters");
                System.out.println("9. Back to User Type Selection");

                System.out.print("Enter your choice: ");
                int adminChoice = scanner.nextInt();

                switch (adminChoice) {
                    case 1:
                        System.out.print("Enter Candidate ID: ");
                        int candidateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Candidate Name: ");
                        String candidateName = scanner.nextLine();
                        votingSystem.registerCandidate(candidateId, candidateName, "candidates.txt");
                        break;
                    case 2:
                        System.out.print("Enter Candidate ID to update: ");
                        int CandidateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter New Candidate Name: ");
                        String newCandidateName = scanner.nextLine();
                        votingSystem.updateCandidate(CandidateId, newCandidateName);

                        break;
                    case 3:
                        System.out.print("Enter Candidate ID to delete: ");
                        int deleteCandidateId = scanner.nextInt();
                        votingSystem.deleteCandidate(deleteCandidateId, "candidates.txt");
                        break;
                    case 4:
                        votingSystem.viewCandidates();
                        break;
                    case 5:
                        System.out.print("Enter Voter ID: ");
                        int voterId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Voter Name: ");
                        String voterName = scanner.nextLine();
                        votingSystem.registerVoter(voterId, voterName, "voters.txt");
                        break;
                    case 6:
                        System.out.print("Enter Voter ID to update: ");
                        int VoterId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter New Voter Name: ");
                        String updateVoterName = scanner.nextLine();
                        votingSystem.updateVoter(VoterId, updateVoterName);
                        break;
                    case 7:
                        System.out.print("Enter Voter ID to delete: ");
                        int deleteVoterId = scanner.nextInt();
                        votingSystem.deleteVoter(deleteVoterId, "voters.txt");
                        break;
                    case 8:
                        votingSystem.viewVoters();
                        break;
                    case 9:
                        // Back to user type selection
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else if (userType == 2) {
                // User Menu
                System.out.println("\nUser Menu:");
                System.out.println("1. Register Candidate");
                System.out.println("2. View List of Candidates");
                System.out.println("3. Register Voter");
                System.out.println("4. View List of Voters");
                System.out.println("5. Back to User Type Selection");

                System.out.print("Enter your choice: ");
                int userChoice = scanner.nextInt();

                switch (userChoice) {
                    case 1:
                        System.out.print("You do not have permission to register candidates.");
                        break;
                    case 2:
                        votingSystem.viewCandidates();
                        break;
                    case 3:
                        System.out.print("You do not have permission to register voters.");
                        break;
                    case 4:
                        votingSystem.viewVoters();
                        break;
                    case 5:
                        // Back to user type selection
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else if (userType == 3) {
                // Exit the program
                System.out.println("Exiting the program.");
                break;
            } else {
                System.out.println("Invalid user type. Please try again.");
            }
        }

        scanner.close();
    }
}
