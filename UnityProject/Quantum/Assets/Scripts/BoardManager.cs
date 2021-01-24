using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine;
using Random = UnityEngine.Random;
using UnityEditor;

//FALTA: LEVEL MODIFICATION+ MODIFICAR MAPAS PARA TENER ALGO QUE HAGA DAÑO

public class BoardManager : MonoBehaviour
{
    [Serializable]
    public class Count
    {
        public int minimum;
        public int maximum;
        public Count(int min, int max)
        {
            minimum = min;
            maximum = max;
        }
    }
    public int columns = 29;
    public int rows = 29;
    public GameObject exit;
    public GameObject[] floorTiles;
    public GameObject[] items; //crystals,pills,pista
    public GameObject[] innerwallTiles;
    public GameObject[] outerWallTiles;
	public GameObject[] floorDamage;
    public GameObject[] signals;
    public GameObject player;
	public int level=1;


    private GameObject mainCamera;

    private Transform boardHolder;
    private List<Vector3> gridPositionsCleanerPart1 = new List<Vector3>(); 

    public void Start()
    {
        //mainCamera = GameObject.FindWithTag("MainCamera");
    }


    public void BoardSetup(int level)
    {
        GameObject board = new GameObject("Board");
        ObjectFactory.AddComponent<CompositeCollider2D>(board);
        boardHolder = board.transform;
        boardHolder.GetComponent<Rigidbody2D>().bodyType = RigidbodyType2D.Static;
        GameObject playerinstance = new GameObject("Player");

        String map =
            "#############################\r\n"
			+ "# I       I         I     I #\r\n"
			+ "# IIIIIII I  H     II IIIII #\r\n"
			+ "#     I  C              I   #\r\n"
			+ "# IIIII IIIII I I I IIIIIII #\r\n"
			+ "#       I     IDI I I   I   #\r\n"
			+ "# IIIIIII III I I III I III #\r\n"
			+ "# I  C  I I  DI I     I   I #\r\n"
			+ "# I IIIII I I I III IIIII I #\r\n"
			+ "#   I   I I I I I  D  I I   #\r\n"
			+ "#II I I IIIII III I I I ITII#\r\n"
			+ "# I   I C   U  C  I I   I   #\r\n"
			+ "# III III I I III I I III I #\r\n"
			+ "#   C I   I I I   I I     I #\r\n"
			+ "# IIIIIIIIIII IIIII IIIIIII #\r\n"
			+ "#  CI I   CD  I  C  H I     #\r\n"
			+ "# I I III IIIII IIIIIII IIII#\r\n"
			+ "# I I   C     I D I  C  I   #\r\n"
			+ "# I III III I IIIII I III I #\r\n"
			+ "# I I I   I I D I   I  C  I #\r\n"
			+ "#CI I IIIII III I IIIIIIIII #\r\n"
			+ "# I I   C   I I I I  C    I #\r\n"
			+ "# I IIIII I I III I IIIII I #\r\n"
			+ "# I D I C ID  I D I I     I #\r\n"
			+ "#IIII I III III IIIII III I #\r\n"
			+ "#  C  I I C IH  I I I I C   #\r\n"
			+ "# III I I I I III I III I I #\r\n"
			+ "# I   I I I I  EI       I I #\r\n"
			+ "#############################\r\n";  

        map = map.Replace("\r\n", "\n");
        String[] lines = map.Split('\n');
        String[] size = lines[0].Split(' ');
        int columns = 29;
        int rows = 29;


        for (int y = 0; y < rows; y++)
        {
            for (int x = 0; x < columns; x++)
            {
                char chr = lines[y][x];
                switch (chr)
                {
                case '#': //Outer Wall UPDATE SEGUN LEVEL
                    GameObject toInstantiate = outerWallTiles[Random.Range(0, outerWallTiles.Length)];
                    GameObject instance = Instantiate(toInstantiate, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
                    instance.transform.SetParent(boardHolder);
                    break;

                case 'E': //EXIT
                    GameObject instanceExit = Instantiate(exit, new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceExit.transform.SetParent(boardHolder);
                    intantiateFloor(x, rows - y, rows, columns);
                    break;

                case 'C': // cristal
                    GameObject instanceCrystal = Instantiate(items[0], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceCrystal.transform.SetParent(boardHolder);
                    intantiateFloor(x, rows - y, rows, columns);
                    break;

                case 'P'://Player
                    //playerinstance = Instantiate(player, new Vector3(x, rows - y, 0f), Quaternion.identity);
                    intantiateFloor(x, rows - y, rows, columns);
                    break;

                case 'I': //IWall UPDATE SEGUN LEVEL
                    GameObject toInstantiate3 = innerwallTiles[Random.Range(0, innerwallTiles.Length)];
                    GameObject instanceIwall = Instantiate(toInstantiate3, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
                    instanceIwall.transform.SetParent(boardHolder);
                    break;

                case 'D'://FloorDamage
                    GameObject toInstantiate2 = floorDamage[Random.Range(0, floorDamage.Length)];
                    GameObject instanceDwall = Instantiate(toInstantiate2, new Vector3(x, rows-y, 0f), Quaternion.identity) as GameObject;
                    instanceDwall.transform.SetParent(boardHolder);
                    break;

                case 'H': //pills
                    GameObject instancePills = Instantiate(items[1], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instancePills.transform.SetParent(boardHolder);
                    intantiateFloor(x, rows - y, rows, columns);
                    break;

                case 'M': //Pista
                    GameObject instancePista = Instantiate(items[2], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instancePista.transform.SetParent(boardHolder);
                    intantiateFloor(x, rows - y, rows, columns);
                    break;

                case 'T': //SAbajo
                    GameObject instanceSAbajo = Instantiate(signals[0], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceSAbajo.transform.SetParent(boardHolder);
                    intantiateFloor(x, rows - y, rows, columns);
                    break;

                case 'Y': //SArriba
                    GameObject instanceSArriba = Instantiate(signals[1], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceSArriba.transform.SetParent(boardHolder);
                    intantiateFloor(x, rows - y, rows, columns);
                    break;

                case 'U': //SDerecha
                    GameObject instanceSDerecha = Instantiate(signals[2], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceSDerecha.transform.SetParent(boardHolder);
                    intantiateFloor(x, rows - y, rows, columns);
                    break;


                case 'O': //SIzquierda                     
                    GameObject instanceSIzquierda = Instantiate(signals[3], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceSIzquierda.transform.SetParent(boardHolder);
                    intantiateFloor(x, rows - y, rows, columns);
                    break;


                case 'S': //SDeath                     
                    GameObject instanceSDeath = Instantiate(signals[4], new Vector3(x, rows - y, 0f), Quaternion.identity) as GameObject;
                    instanceSDeath.transform.SetParent(boardHolder);
                    intantiateFloor(x, rows - y, rows, columns);
                    break;

                default:
                    intantiateFloor(x, rows - y, rows, columns);
                    break;

                }
            }


        }
		

        //CameraFollow camerascript = mainCamera.GetComponent<CameraFollow>();
        //camerascript.following = playerinstance;
		


    }

    public void intantiateFloor(int x, int y, int rows, int columns)
    {
        GameObject floorToInstatiate = floorTiles[Random.Range(0, floorTiles.Length)];
        //if (x == 1 || x == columns - 2 || y == 2 || y == rows - 1)
        //    floorToInstatiate = outerWallTiles[Random.Range(0, outerWallTiles.Length)];
		
        GameObject instanceFloor = Instantiate(floorToInstatiate, new Vector3(x, y, 0f), Quaternion.identity) as GameObject;
        instanceFloor.transform.SetParent(boardHolder);
    }



    public void SetUpScene()
    {
        BoardSetup(level);
        //LayoutObjectAtRandom(cleanersSprite, cleanerCount.minimum, cleanerCount.maximum);

    }


}
