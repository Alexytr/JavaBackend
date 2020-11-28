package interpreter;

public class Lexer
{
    private boolean inLoop;
    private StringBuilder stringBuilder;

    public Lexer()
    {
        inLoop = false;
        stringBuilder = new StringBuilder();
    }

    public String[] Lexer(String line)
    {
        String[] strings;
        String line1 = line.trim().replaceAll(" +", " ");

        if (line1.startsWith("while") || line1.startsWith("if"))
        {
            inLoop = true;
            stringBuilder.append(line + " /n ");
        }
        else if (inLoop == true)
        {
            stringBuilder.append(line1 + " /n ");

            if (line1.endsWith("}"))
            {
                inLoop = false;
                strings = stringBuilder.toString().split("((?<=[a-zA-Z0-9={}])\\s(?=[a-zA-Z0-9={}(]))|[\\n\\r]+|((?<=[{}])|(?=[{}]))|((?<=[=])|(?=[=]))");
                stringBuilder = new StringBuilder();

                return strings;
            }
        }

        return line1.split("((?<=[a-zA-Z0-9={}])\\s(?=[a-zA-Z0-9={}(]))|[\\n\\r]+|((?<=[{}])|(?=[{}]))|((?<=[=])|(?=[=]))");
    }
}